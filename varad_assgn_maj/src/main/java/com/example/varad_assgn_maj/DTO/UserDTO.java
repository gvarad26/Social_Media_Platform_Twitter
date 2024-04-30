package com.example.varad_assgn_maj.DTO;

import com.example.varad_assgn_maj.model.Comment;
import com.example.varad_assgn_maj.model.Post;
import com.example.varad_assgn_maj.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    public static class UserSignupRequest {
        private String email;
        private String name;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public static class UserLoginRequest {
        private String email;
        private String password;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class UserDetailDTO {
        private String name;
        private Integer userID;
        private String email;

        public UserDetailDTO(User user) {
            this.name = user.getName();
            this.userID = user.getUserID();
            this.email = user.getEmail();
        }
        public String getName() {
            return name;
        }
        public Integer getUserID() {
            return userID;
        }
        public String getEmail() {
            return email;
        }
    }

    public static class PostWithComments {
        private final Integer postID;
        private final String postBody;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private final Date date;

        private final List<CommentDetails> comments;

        public PostWithComments(Post post) {
            this.postID = post.getPostID();
            this.postBody = post.getPostBody();
            this.date = post.getDate();
            this.comments = post.getComments().stream()
                    .map(CommentDetails::new)
                    .collect(Collectors.toList());
        }

        // Getters for accessing the response details
        public Integer getPostID() {
            return postID;
        }

        public String getPostBody() {
            return postBody;
        }

        public Date getDate() {
            return date;
        }

        public List<CommentDetails> getComments() {
            return comments;
        }
    }

    static class CommentDetails {
        private final Integer commentID;
        private final String commentBody;
        private final CommentCreator commentCreator;

        public CommentDetails(Comment comment) {
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



