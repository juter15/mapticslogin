package kr.co.maptics.mapticslogin.proxy.fallback;

import kr.co.maptics.mapticslogin.model.request.MapTicsLoginRequest;
import kr.co.maptics.mapticslogin.model.response.MapTicsLoginResponse;
import kr.co.maptics.mapticslogin.proxy.MapTicsLoginProxy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapTicsLoginProxyFallback implements MapTicsLoginProxy {
    private final Throwable cause;
    public MapTicsLoginProxyFallback(Throwable cause) {
        this.cause = cause;

    }

    @Override
    public MapTicsLoginResponse mapticsLogin(String Authorization, MapTicsLoginRequest mapTicsLoginRequest) {
        log.error("MapTics login 연동 fail : '{}'", cause);
        log.warn("MapTics login 연동 ['{}'] is missing !!!!", mapTicsLoginRequest);
        return null;
    }
}
