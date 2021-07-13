package kr.co.maptics.mapticslogin.proxy.fallback;

import feign.hystrix.FallbackFactory;
import kr.co.maptics.mapticslogin.proxy.GoodSoftLoginProxy;
import org.springframework.stereotype.Component;

@Component
public class GoodSoftLoginProxyFallbackFactory implements FallbackFactory<GoodSoftLoginProxy> {

    @Override
    public GoodSoftLoginProxy create(Throwable throwable) {
        return new GoodSoftLoginProxyFallback(throwable);
    }
}
