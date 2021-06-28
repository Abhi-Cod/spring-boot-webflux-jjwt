/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abhi.code.springbootwebfluxjjwt.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;
/**
 *
 * @author Avissol
 */
@Component
@RequiredArgsConstructor
public class HttpConfig {

    @Value("${http.port}")
    private int httpPort;

    private WebServer http;
    private final HttpHandler httpHandler;

    @PostConstruct
    public void init() {
        ReactiveWebServerFactory factory = new NettyReactiveWebServerFactory(httpPort);
        this.http = factory.getWebServer(this.httpHandler);
        this.http.start();
    }

    @PreDestroy
    public void shutdown() {
        this.http.stop();
    }
}