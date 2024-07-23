package com.fivechan.forum.context.topic.application;

public class CreateTopic {
    // attributes
    private Long userId;
    private String title;
    private String content;

    // constructor
    public CreateTopic(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    // methods
    public void execute() {}

    // getters y setters :P
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
