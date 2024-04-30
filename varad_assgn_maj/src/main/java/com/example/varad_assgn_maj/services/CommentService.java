package com.example.varad_assgn_maj.services;

import com.example.varad_assgn_maj.model.Comment;
import com.example.varad_assgn_maj.model.Post;
import com.example.varad_assgn_maj.model.User;
import com.example.varad_assgn_maj.repository.CommentRepository;
import com.example.varad_assgn_maj.repository.PostRepository;
import com.example.varad_assgn_maj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createComment(String commentBody, Integer postID, Integer userID) {
        Post post = postRepository.findById(postID).orElse(null);
        User user = userRepository.findById(userID).orElse(null);

        if (post == null || user == null) {
            return; // If post or user does not exist, do nothing
        }

        Comment newComment = new Comment();
        newComment.setCommentBody(commentBody);
        newComment.setPost(post);
        newComment.setUser(user);

        commentRepository.save(newComment);
    }

    public Optional<Comment> findCommentById(Integer commentID) {
        return commentRepository.findById(commentID);
    }

    public void editComment(String newCommentBody,Integer commentID) {
        Comment existingComment = commentRepository.findById(commentID).orElse(null);

        if (existingComment != null) {
            existingComment.setCommentBody(newCommentBody);
            commentRepository.save(existingComment);
        }
    }

    public void deleteComment(Integer commentID) {
        if (commentRepository.existsById(commentID)) {
            commentRepository.deleteById(commentID);
        }
    }

    public boolean postExists(Integer postID) {
        return postRepository.existsById(postID);
    }
}
