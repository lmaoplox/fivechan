package com.fivechan.forum.context.comment.application;

public class DeleteComment {
    // attributes :)
    private Long commentId;

    // Constructor
    public DeleteComment(Long commentId) {
        this.commentId = commentId;
    }

    // methods
    public void execute() {}

    // getter y setters
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
