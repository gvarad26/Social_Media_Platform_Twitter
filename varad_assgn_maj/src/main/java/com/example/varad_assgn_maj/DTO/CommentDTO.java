package com.example.varad_assgn_maj.DTO;

import com.example.varad_assgn_maj.model.Comment;
import com.example.varad_assgn_maj.model.User;

public class CommentDTO {
    public static class CommentCreateRequest {
        private String commentBody;
        private Integer postID;
        private Integer userID;

        public String getCommentBody() {
            return commentBody;
        }

        public void setCommentBody(String commentBody) {
            this.commentBody = commentBody;
        }

        public Integer getPostID() {
            return postID;
        }

        public void setPostID(Integer postID) {
            this.postID = postID;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }
    }

    public static class CommentEditRequest {
        private String commentBody;
        private Integer commentID;

        public String getCommentBody() {
            return commentBody;
        }

        public void setCommentBody(String commentBody) {
            this.commentBody = commentBody;
        }

        public Integer getCommentID() {
            return commentID;
        }

        public void setCommentID(Integer commentID) {
            this.commentID = commentID;
        }
    }

    public static class CommentResponse {
        private final Integer commentID;
        private final String commentBody;
        private final CommentCreator commentCreator;

        public CommentResponse(Comment comment) {
            this.commentID = comment.getCommentID();
            this.commentBody = comment.getCommentBody();
            this.commentCreator = new CommentCreator(comment.getUser());
        }

        public Integer getCommentID() {
            return commentID;
        }

        public String getCommentBody() {
            return commentBody;
        }

        public CommentCreator getCommentCreator() {
            return commentCreator;
        }

        static class CommentCreator {
            private final Integer userID;
            private final String name;

            public CommentCreator(User user) {
                this.userID = user.getUserID();
                this.name = user.getName();
            }

            public Integer getUserID() {
                return userID;
            }

            public String getName() {
                return name;
            }
        }
    }
}
