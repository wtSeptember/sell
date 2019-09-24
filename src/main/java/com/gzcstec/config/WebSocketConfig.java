package com.gzcstec.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.ServletContext;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Component
public class WebSocketConfig {

   @Bean
    public ServerEndpointExporter serverEndpointExporter() {
       return new ServerEndpointExporter();
   }
}
