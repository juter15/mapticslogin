package kr.co.maptics.mapticslogin.proxy;

import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.response.GoodSoftBody;
import kr.co.maptics.mapticslogin.model.response.GoodSoftResponse;
import kr.co.maptics.mapticslogin.proxy.fallback.GoodSoftLoginProxyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "goodsoft-login", url = "${good-soft.server-url}", fallbackFactory = GoodSoftLoginProxyFallbackFactory.class)
public interface GoodSoftLoginProxy {

    @PutMapping("/users")
    GoodSoftResponse<GoodSoftBody> goodsoftLogin(@RequestBody LoginRequest loginRequest);

}
