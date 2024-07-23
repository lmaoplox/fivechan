package com.fivechan.forum.context.comment.application;

public class EditComment {
    //attributes
    private final Long commentId;
    private String newContent;


    //constructor
    public EditComment(Long commentId, String newContent) {
        this.commentId = commentId;
        this.newContent = newContent;
    }


    //methods
    public void execute(){}


    //getter y setters
    public Long getCommentId() {
        return commentId;
    }
    public String getNewContent() {
        return newContent;
    }
    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
