package kr.co.maptics.mapticslogin.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import kr.co.maptics.mapticslogin.entity.QStorePayment;
import kr.co.maptics.mapticslogin.entity.StorePayment;
import kr.co.maptics.mapticslogin.model.MapTicsResultCode;
import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.request.MapTicsLoginRequest;
import kr.co.maptics.mapticslogin.model.response.*;
import kr.co.maptics.mapticslogin.proxy.GoodSoftLoginProxy;
import kr.co.maptics.mapticslogin.proxy.MapTicsLoginProxy;
import kr.co.maptics.mapticslogin.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {


    private final GoodSoftLoginProxy goodSoftLoginProxy;
    private final MapTicsLoginProxy mapTicsLoginProxy;

    private final EncryptUtil platformEncryptUtil;
    private final QStorePayment qStorePayment = QStorePayment.storePayment;
    private final EntityManager entityManager;


    @Value(value = "${maptics.Authorization}")
    private String Authorization; //추후 변경

    public MapTicsLoginResponse accountLogin(@NotNull LoginRequest loginRequest) {

        loginRequest.setPassword(platformEncryptUtil.aesEncode(loginRequest.getPassword()));
        loginRequest.setOs("web");

        try {
            GoodSoftResponse<GoodSoftBody> goodSoftLoginResponse = goodSoftLoginProxy.goodsoftLogin(loginRequest);
            log.info("goodSoftLoginResponse: {}", goodSoftLoginResponse);
            if (goodSoftLoginResponse != null && goodSoftLoginResponse.getStatus().getMessage().equals("Success.")) {
                Tuple store_payment = new JPAQuery<StorePayment>(entityManager)
                        .select(qStorePayment.StorePaymentSeq, qStorePayment.StoreSeq, qStorePayment.StartDate, qStorePayment.EndDate)
                        .from(qStorePayment)
                        .where(qStorePayment.StoreSeq.eq(Integer.parseInt(goodSoftLoginResponse.getBody().getStoreCode())))

                        .fetchOne();
                if (store_payment != null && periodCheck(store_payment)) {
                    MapTicsLoginRequest mapTicsLoginRequest = new MapTicsLoginRequest();
                    mapTicsLoginRequest.setMember_num(loginRequest.getId());
                    mapTicsLoginRequest.setLogin_type("bizniz");
                    log.info("loginRequest.getId(): {}", loginRequest.getId());
                    //maptics login 연동
                    MapTicsLoginResponse mapTicsLoginResponse = mapTicsLoginProxy.mapticsLogin(Authorization, mapTicsLoginRequest);
                    log.info("mapTicsLoginResponse: {}", mapTicsLoginResponse);
                    if (mapTicsLoginResponse != null && mapTicsLoginResponse.getCode().equals(MapTicsResultCode.OK.getCode())) {
                        mapTicsLoginResponse.setLoginResult("SUCCESS");
                        return mapTicsLoginResponse;
                    } else {
                        return null;
                    }
                } else {
                    log.info("EXPIRES");
                    MapTicsLoginResponse mapTicsLoginResponse = new MapTicsLoginResponse();
                    mapTicsLoginResponse.setLoginResult("EXPIRES");
                    return mapTicsLoginResponse;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    private boolean periodCheck(@NotNull Tuple searchStorePeriod) {
		if(searchStorePeriod != null){
		Date startDate = searchStorePeriod.get(qStorePayment.StartDate);
		Date endDate = searchStorePeriod.get(qStorePayment.EndDate);
        	log.info("startDate: {}",startDate);
        	log.info("endDate: {}",endDate);
            LocalDateTime today = LocalDateTime.now();
            log.info("today: {}", today);
            return today.isAfter(convertLDT(startDate)) && today.isBefore(convertLDT(endDate));
        } else {
            return false;
        }
    }

    private LocalDateTime convertLDT(Date Period) {
        LocalDateTime ldt = Instant.ofEpochMilli(Period.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return ldt;
    }
}
