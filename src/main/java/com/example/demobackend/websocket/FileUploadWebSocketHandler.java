package com.example.demobackend.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FileUploadWebSocketHandler extends TextWebSocketHandler {

    // 存储所有的 WebSocket 连接
    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String sessionId = session.getId(); // 获取 WebSocket 会话 ID
        sessions.put(sessionId, session); // 存储 WebSocket 连接
        System.out.println("WebSocket 连接建立: " + sessionId);
        // 立即发送 sessionId 给前端
        session.sendMessage(new TextMessage("{\"sessionId\": \"" + sessionId + "\"}"));
        System.out.println("WebSocket 连接建立: " + sessionId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("收到消息: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
        System.out.println("WebSocket 连接关闭: " + session.getId());
    }

    public void sendProgress(String sessionId, String message) {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                synchronized (session) {  // 确保一个线程在操作 WebSocket
                    System.out.println("发送 WebSocket 消息: " + message);
                    session.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                System.err.println("WebSocket session 不存在或已关闭: " + sessionId);
            }
        }
    }

}
