package com.network.drawingboard.model;

public class DrawingData {
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private String color;
    private int lineWidth;
    private String action; // "draw", "start", "end", "clear"
    private String userId;

    // 기본 생성자
    public DrawingData() {}

    // 전체 매개변수 생성자
    public DrawingData(int x, int y, int prevX, int prevY, String color, int lineWidth, String action, String userId) {
        this.x = x;
        this.y = y;
        this.prevX = prevX;
        this.prevY = prevY;
        this.color = color;
        this.lineWidth = lineWidth;
        this.action = action;
        this.userId = userId;
    }

    // Getter와 Setter 메서드들
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPrevX() {
        return prevX;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DrawingData{" +
                "x=" + x +
                ", y=" + y +
                ", prevX=" + prevX +
                ", prevY=" + prevY +
                ", color='" + color + '\'' +
                ", lineWidth=" + lineWidth +
                ", action='" + action + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}