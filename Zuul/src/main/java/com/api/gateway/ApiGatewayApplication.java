package com.api.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by ruike on 6/20/18.
 */


@SpringCloudApplication
@EnableZuulProxy
public class ApiGatewayApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
    }
}
