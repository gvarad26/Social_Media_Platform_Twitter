package com.example.varad_assgn_maj.controller;

import com.example.varad_assgn_maj.services.CommentService;
import com.example.varad_assgn_maj.services.UserService;
import com.example.varad_assgn_maj.model.Comment;
import com.example.varad_assgn_maj.DTO.CommentDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam Integer commentID) {
        Optional<Comment> commentOptional =commentService.findCommentById(commentID);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentResponse response = new CommentResponse(comment);
            return ResponseEntity.status(200).body(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error","Comment does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateRequest request) {
        if (!userService.findUserById(request.getUserID()).isPresent()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error","User does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
        if (!commentService.postExists(request.getPostID())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error","Post does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
        commentService.createComment(request.getCommentBody(), request.getPostID(), request.getUserID());
        return ResponseEntity.status(200).body("Comment created successfully");
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody CommentEditRequest commentEditRequest) {
        if (commentService.findCommentById(commentEditRequest.getCommentID()).isPresent()) {
            commentService.editComment(commentEditRequest.getCommentBody(),commentEditRequest.getCommentID());
            return ResponseEntity.status(200).body("Comment edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam Integer commentID) {
        if (commentService.findCommentById(commentID).isPresent()) {
            commentService.deleteComment(commentID);
            return ResponseEntity.status(200).body("Comment deleted");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

}