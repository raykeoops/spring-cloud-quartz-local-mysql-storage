package com.eureka.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.Servlet;
import java.util.List;

/**
 * Created by ruike on 6/20/18.
 */

@RestController
public class DcController implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    @Autowired
    private DiscoveryClient discoveryClient;

    private static final Logger logger = LoggerFactory.getLogger(DcController.class);

    private int serverPort;

    @GetMapping("/dc")
    String dc() {
        ServiceInstance instance = discoveryClient.getInstances("eureka-service-provider").get(0);
        String url = instance.getHost() + ":" + serverPort;
        System.out.println("Connected to: " + url);

        return "Connected to Service Provider";
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        this.serverPort = event.getEmbeddedServletContainer().getPort();
    }

}
