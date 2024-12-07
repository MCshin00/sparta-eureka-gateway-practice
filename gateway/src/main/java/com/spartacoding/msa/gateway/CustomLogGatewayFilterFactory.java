package com.spartacoding.msa.gateway;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * // TODO order-service에 특정 uri에만 적용될 log filter를 구현해주세요!
 */
@Component
public class CustomLogGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomLogGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            logMessage(config, "Request Path : " + exchange.getRequest().getPath());
            if (config.isLogHeaders()) {
                logMessage(config, "Request Headers : " + exchange.getRequest().getHeaders());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logMessage(config, "Response Status : " + exchange.getResponse().getStatusCode());

                if (config.isLogHeaders()) {
                    logMessage(config, "Response Headers : " + exchange.getResponse().getHeaders());
                }
            }));
        };
    }

    private void logMessage(Config config, String message) {
        logger.info("{}, {}", config.getBaseMessage(), message);
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        // Configuration properties
        // TODO 로그가 출력되는 포맷을 설정하는 것도 좋을 것 같아요
        private String baseMessage = "[CustomLogFilter 동작]";
        private boolean logHeaders = false;
    }

    public CustomLogGatewayFilterFactory() {
        super(Config.class);
    }
}
