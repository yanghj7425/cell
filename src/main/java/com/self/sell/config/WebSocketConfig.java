package com.self.sell.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {

    /**
     * <pre>
     *      单元测试时会报错，需注释
     *  </pre>
     *
     * @return
     */
//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
