package com.fivechan.forum.context.topic.application;

public class DeleteTopic {
    //attributes :)
    private Long topicId;

    //constructor
    public DeleteTopic(Long topicId) {
        this.topicId = topicId;
    }
    //methods :)
    public void execute(){}

    //getter y setters
    public Long getTopicId() {
        return topicId;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
