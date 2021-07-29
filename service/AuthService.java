package kr.co.maptics.mapticslogin.service;

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

import javax.validation.constraints.NotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {



	private final GoodSoftLoginProxy goodSoftLoginProxy;
	private final MapTicsLoginProxy mapTicsLoginProxy;

	private final EncryptUtil platformEncryptUtil;

	@Value(value = "${maptics.Authorization}")
	private String Authorization; //추후 변경

	public MapTicsLoginResponse accountLogin(@NotNull LoginRequest loginRequest) {

		loginRequest.setPassword(platformEncryptUtil.aesEncode(loginRequest.getPassword()));
		loginRequest.setOs("web");
		log.info("service: {}", loginRequest);



		try {
			GoodSoftResponse<GoodSoftBody> goodSoftLoginResponse = goodSoftLoginProxy.goodsoftLogin(loginRequest);
			log.info("goodSoftLoginResponse: {}", goodSoftLoginResponse);
			if(goodSoftLoginResponse != null && goodSoftLoginResponse.getStatus().getMessage().equals("Success.")){
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

}
