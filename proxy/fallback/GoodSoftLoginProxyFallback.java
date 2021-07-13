package kr.co.maptics.mapticslogin.proxy.fallback;

import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.response.GoodSoftBody;
import kr.co.maptics.mapticslogin.model.response.GoodSoftResponse;
import kr.co.maptics.mapticslogin.proxy.GoodSoftLoginProxy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodSoftLoginProxyFallback implements GoodSoftLoginProxy {
    private final Throwable cause;

    public GoodSoftLoginProxyFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public GoodSoftResponse<GoodSoftBody> goodsoftLogin(LoginRequest loginRequest) {
        log.error("GoodSoft login 연동 fail : '{}'", cause);
        log.warn("GoodSoft  login 연동 ['{}'] is missing !!!!", loginRequest);
        return null;
    }

/*    @Override
    public GoodSoftResponse<GoodSoftBody> goodsoftLogin(LoginRequest loginRequest) {

        return null;
    }*/
}
