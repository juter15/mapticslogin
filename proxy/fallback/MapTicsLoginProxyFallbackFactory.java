package kr.co.maptics.mapticslogin.proxy.fallback;

import feign.hystrix.FallbackFactory;
import kr.co.maptics.mapticslogin.proxy.MapTicsLoginProxy;
import org.springframework.stereotype.Component;

@Component
public class MapTicsLoginProxyFallbackFactory implements FallbackFactory<MapTicsLoginProxy> {
    @Override
    public MapTicsLoginProxy create(Throwable throwable) {
        return new MapTicsLoginProxyFallback(throwable);
    }
}
