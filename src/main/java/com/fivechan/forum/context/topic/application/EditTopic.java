package com.fivechan.forum.context.topic.application;

public class EditTopic {
    //attributes
    private Long topicId;
    private String newTitle;
    private String newContent;

    public EditTopic(Long topicId, String newTitle, String newContent) {
        this.topicId = topicId;
        this.newTitle = newTitle;
        this.newContent = newContent;
    }
    //methods :)
    public void execute(){}

    //getters y setters :p
    public Long getTopicId() {
        return topicId;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    public String getNewTitle() {
        return newTitle;
    }
    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
    public String getNewContent() {
        return newContent;
    }
    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
