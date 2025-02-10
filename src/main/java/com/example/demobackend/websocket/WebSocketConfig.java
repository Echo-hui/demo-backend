package com.example.demobackend.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final FileUploadWebSocketHandler fileUploadWebSocketHandler;

    public WebSocketConfig(FileUploadWebSocketHandler fileUploadWebSocketHandler) {
        this.fileUploadWebSocketHandler = fileUploadWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(fileUploadWebSocketHandler, "/upload-progress").setAllowedOrigins("*");
    }
}
