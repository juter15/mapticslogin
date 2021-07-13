package kr.co.maptics.mapticslogin.proxy;

import kr.co.maptics.mapticslogin.model.request.MapTicsLoginRequest;
import kr.co.maptics.mapticslogin.model.response.MapTicsLoginResponse;
import kr.co.maptics.mapticslogin.proxy.fallback.MapTicsLoginProxyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "login-service", url = "${maptics.service-url}", fallbackFactory = MapTicsLoginProxyFallbackFactory.class)
public interface MapTicsLoginProxy {

    @PostMapping("/get-maptics-url")
    MapTicsLoginResponse mapticsLogin(@RequestHeader("Authorization") String Authorization,
                                       @RequestBody MapTicsLoginRequest mapTicsLoginRequest);

}
