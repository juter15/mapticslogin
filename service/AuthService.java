package kr.co.maptics.mapticslogin.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.maptics.mapticslogin.entity.*;
import kr.co.maptics.mapticslogin.exception.NotFoundEntityException;
import kr.co.maptics.mapticslogin.model.MapTicsResultCode;
import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.request.MapTicsLoginRequest;
import kr.co.maptics.mapticslogin.model.response.*;
import kr.co.maptics.mapticslogin.proxy.GoodSoftLoginProxy;
import kr.co.maptics.mapticslogin.proxy.MapTicsLoginProxy;
import kr.co.maptics.mapticslogin.repository.*;
import kr.co.maptics.mapticslogin.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthService {

//	@Value("${pocket-mobile.platform-secure-key}")
//	private String platformSecureKey;
//
//	@Value("${pocket-mobile.campaign-secure-key}")
//	private String campaignSecureKey;

	private final StoreUserRepository storeUserRepository;
	private final StoreUserAuthRepository storeUserAuthRepository;

	private final UsersRepository usersRepository;
	private final UsersAuthRepository usersAuthRepository;

	private final GoodSoftLoginProxy goodSoftLoginProxy;
	private final MapTicsLoginProxy mapTicsLoginProxy;

	private final EncryptUtil platformEncryptUtil;

	private final QStoreUser qStoreUsers = QStoreUser.storeUser;
	private final EntityManager entityManager;
	@Value(value = "${maptics.Authorization}")
	private String Authorization; //추후 변경

	public MapTicsLoginResponse accountLogin(@NotNull LoginRequest loginRequest) {

		loginRequest.setPassword(platformEncryptUtil.aesEncode(loginRequest.getPassword()));
		loginRequest.setOs("web");
		log.info("service: {}", loginRequest);

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);


		try {
			GoodSoftResponse<GoodSoftBody> goodSoftLoginResponse = goodSoftLoginProxy.goodsoftLogin(loginRequest);
			log.info("goodSoftLoginResponse: {}", goodSoftLoginResponse);
			if(goodSoftLoginResponse != null &&goodSoftLoginResponse.getStatus().getMessage().equals("Success.")){
				MapTicsLoginRequest mapTicsLoginRequest = new MapTicsLoginRequest();
				mapTicsLoginRequest.setMember_num(loginRequest.getId());
				mapTicsLoginRequest.setLogin_type("bizniz");
				log.info("loginRequest.getId(): {}", loginRequest.getId());
				//maptics login 연동
				MapTicsLoginResponse mapTicsLoginResponse = mapTicsLoginProxy.mapticsLogin(Authorization, mapTicsLoginRequest);
				log.info("mapTicsLoginResponse: {}", mapTicsLoginResponse);
				if(mapTicsLoginResponse != null && mapTicsLoginResponse.getCode().equals(MapTicsResultCode.OK.getCode())){
					return mapTicsLoginResponse;
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
		}catch (Exception e){
			return null;
		}


	}

	private LoginResponse buildLoginResponse(@NotNull GoodSoftBody body) {
		LoginResponse response = new LoginResponse()
				.setAuthCode(body.getAuthCode())
				.setName(body.getUserName())
				.setGrade(body.getUserGrade())
				.setStatus(body.getUserStatus())
				.setType(body.getUserType());

		if ("P".equals(body.getUserType())) {
			UsersAuth authInfo = usersAuthRepository.findByAuthCode(body.getAuthCode());
			Users user = usersRepository.findById(authInfo.getUserSeq()).orElseThrow(() -> new NotFoundEntityException("회원정보 없음."));
			response.setUserNo(authInfo.getUserSeq())
					.setUserId(user.getUserId());

		} else {
			StoreUserAuth authInfo = storeUserAuthRepository.findByAuthCode(body.getAuthCode());
			StoreUser user = storeUserRepository.findById(authInfo.getStoreUserSeq()).orElseThrow(() -> new NotFoundEntityException("회원정보 없음."));
			response.setUserNo(authInfo.getStoreUserSeq())
					.setStoreCode(authInfo.getStoreSeq())
					.setUserId(user.getUserId());
		}

		return response;
	}

}
