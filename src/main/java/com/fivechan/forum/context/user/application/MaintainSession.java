package com.fivechan.forum.context.user.application;

public class MaintainSession {
    // attributes :P
    private Long userId;
    private boolean isSessionActive;

    // constructor
    public MaintainSession(Long userId, boolean isSessionActive) {
        this.userId = userId;
        this.isSessionActive = isSessionActive;
    }

    // methods
    public void execute() {}

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isSessionActive() {
        return isSessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        isSessionActive = sessionActive;
    }
}
