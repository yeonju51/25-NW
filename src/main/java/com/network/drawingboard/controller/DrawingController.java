package com.network.drawingboard.controller;

import com.network.drawingboard.model.DrawingData;
import com.network.drawingboard.model.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class DrawingController {

    private static final Logger logger = LoggerFactory.getLogger(DrawingController.class);
    private final SimpMessageSendingOperations messagingTemplate;

    // 접속자 수 관리
    private final AtomicInteger userCount = new AtomicInteger(0);
    // 세션별 사용자 ID 저장
    private final ConcurrentHashMap<String, String> sessionUsers = new ConcurrentHashMap<>();

    public DrawingController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 그리기 데이터 처리
     */
    @MessageMapping("/draw")
    public void handleDrawing(DrawingData drawingData, StompHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        String userId = sessionUsers.get(sessionId);

        if (userId != null) {
            drawingData.setUserId(userId);
            logger.info("Drawing data from user {}: {}", userId, drawingData);

            // 모든 클라이언트에게 그리기 데이터 브로드캐스트
            messagingTemplate.convertAndSend("/topic/drawing", drawingData);
        }
    }

    /**
     * 캔버스 초기화 요청 처리
     */
    @MessageMapping("/clear")
    public void handleClear(StompHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        String userId = sessionUsers.get(sessionId);

        if (userId != null) {
            logger.info("Canvas clear requested by user: {}", userId);

            DrawingData clearData = new DrawingData();
            clearData.setAction("clear");
            clearData.setUserId(userId);

            // 모든 클라이언트에게 초기화 신호 전송
            messagingTemplate.convertAndSend("/topic/drawing", clearData);
        }
    }

    /**
     * 사용자 연결 이벤트 처리
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        // 새로운 사용자 ID 생성
        String userId = "User" + System.currentTimeMillis();
        sessionUsers.put(sessionId, userId);

        int currentUserCount = userCount.incrementAndGet();

        logger.info("New user connected: {} (Session: {}), Total users: {}",
                userId, sessionId, currentUserCount);

        UserStatus userStatus = new UserStatus(userId, "join", currentUserCount,
                userId + "님이 접속했습니다.");

        // 모든 클라이언트에게 사용자 접속 알림
        messagingTemplate.convertAndSend("/topic/users", userStatus);
    }

    /**
     * 사용자 연결 해제 이벤트 처리
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        String userId = sessionUsers.remove(sessionId);

        if (userId != null) {
            int currentUserCount = userCount.decrementAndGet();

            logger.info("User disconnected: {} (Session: {}), Remaining users: {}",
                    userId, sessionId, currentUserCount);

            UserStatus userStatus = new UserStatus(userId, "leave", currentUserCount,
                    userId + "님이 나갔습니다.");

            // 모든 클라이언트에게 사용자 퇴장 알림
            messagingTemplate.convertAndSend("/topic/users", userStatus);
        }
    }
}