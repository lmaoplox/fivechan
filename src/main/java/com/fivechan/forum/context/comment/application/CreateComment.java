package com.fivechan.forum.context.comment.application;

public class CreateComment {
    // attributes :)
    private Long topicId;
    private Long userId;
    private String content;

    // constructor
    public CreateComment(Long topicId, Long userId, String content) {
        this.topicId = topicId;
        this.userId = userId;
        this.content = content;
    }

    // methods
    public void execute() {}

    // getters y setters :P
    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
