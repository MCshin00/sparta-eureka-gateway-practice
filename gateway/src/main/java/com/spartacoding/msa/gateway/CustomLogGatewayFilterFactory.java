package com.spartacoding.msa.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * // TODO order-service에 특정 uri에만 적용될 log filter를 구현해주세요!
 */
@Component
public class CustomLogGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomLogGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

        };
    }

    public static class Config {
        // Configuration properties
        // TODO 로그가 출력되는 포맷을 설정하는 것도 좋을 것 같아요
    }

    public CustomLogGatewayFilterFactory() {
        super(Config.class);
    }
}
