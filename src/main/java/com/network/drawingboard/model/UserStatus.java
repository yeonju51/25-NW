package com.network.drawingboard.model;

public class UserStatus {
    private String userId;
    private String action; // "join", "leave"
    private int userCount;
    private String message;

    // 기본 생성자
    public UserStatus() {}

    // 생성자
    public UserStatus(String userId, String action, int userCount, String message) {
        this.userId = userId;
        this.action = action;
        this.userCount = userCount;
        this.message = message;
    }

    // Getter와 Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                ", userCount=" + userCount +
                ", message='" + message + '\'' +
                '}';
    }
}