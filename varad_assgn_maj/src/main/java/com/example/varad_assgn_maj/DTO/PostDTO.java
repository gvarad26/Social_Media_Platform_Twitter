package com.example.varad_assgn_maj.DTO;

import com.example.varad_assgn_maj.model.Post;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class PostDTO {
    public static class PostCreateRequest {
        private String postBody;
        private Integer userID;

        // Getters and Setters
        public String getPostBody() {
            return postBody;
        }

        public void setPostBody(String postBody) {
            this.postBody = postBody;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }
    }

    public static class PostDetailsResponse {
        private final Integer postID;
        private final String postBody;

        @Temporal(TemporalType.DATE)
        private final Date date;

        private final List<CommentDTO.CommentResponse> comments; // Include comment details

        public PostDetailsResponse(Post post, List<CommentDTO.CommentResponse> commentResponses) {
            this.postID = post.getPostID();
            this.postBody = post.getPostBody();
            this.date = post.getDate();
            this.comments = commentResponses;
        }


        public Integer getPostID() {
            return postID;
        }

        public String getPostBody() {
            return postBody;
        }

        public Date getDate() {
            return date;
        }

        public List<CommentDTO.CommentResponse> getComments() {
            return comments;
        }
    }

    public static class PostEditRequest {
        private String postBody;
        private Integer postID;

        // Getters and Setters
        public String getPostBody() {
            return postBody;
        }

        public void setPostBody(String postBody) {
            this.postBody = postBody;
        }

        public Integer getPostID() {
            return postID;
        }

        public void setPostID(Integer postID) {
            this.postID = postID;
        }


    }

}
