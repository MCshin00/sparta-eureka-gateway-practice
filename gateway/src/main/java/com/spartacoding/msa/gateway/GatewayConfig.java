package com.spartacoding.msa.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// TODO Java-code로 라우팅을 설정해주세요! (여기에서 설정하면 application.yml에서는 설정하지 않아도 됩니다)
// 아래는 예시입니다. 삭제 후 실제 코드 추가해주세요!
public class GatewayConfig {

    /**
     * 예시입니다.
     * CustomFilter 라는 가상의 클래스에 필요한 config 값을 설정하고, "sparta-service"라는 라우팅 룰을 추가하는 내용입니다.
     * @param builder
     * @param filter Custom filter 이름
     * @return
     */
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder, CustomFilter filter) {
        return builder.routes()
                .route("sparta-service", r -> r.path("/sparta/**")
                        .filters(f -> f.filter(filter.apply(new CustomFilter.Config() {{
                            setClassType("sparta-class");
                            setLectureName("eureka-gateway");
                        }})))
                        .uri("lb://sparta-service")
                )
                .build();
    }
}
