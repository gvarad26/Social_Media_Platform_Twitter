package com.example.varad_assgn_maj.controller;

import com.example.varad_assgn_maj.services.PostService;
import com.example.varad_assgn_maj.services.CommentService;
import com.example.varad_assgn_maj.model.Post;
import com.example.varad_assgn_maj.DTO.PostDTO.*;
import com.example.varad_assgn_maj.DTO.CommentDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request) {
        Map<String, String> errorResponse = new HashMap<>(); // Create a map for error responses

        if (postService.findUserById(request.getUserID()).isEmpty()) {
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }

        Post newPost = postService.createPost(request.getPostBody(), request.getUserID());
        return ResponseEntity.status(200).body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getPostDetails(@RequestParam Integer postID) {
        Optional<Post> postOptional = postService.findPostById(postID);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            List<CommentResponse> commentResponses = post.getComments().stream()
                    .map(CommentResponse::new) // Convert each comment to CommentResponse
                    .collect(Collectors.toList());

            PostDetailsResponse postDetailsResponse = new PostDetailsResponse(post, commentResponses);

            return ResponseEntity.status(200).body(postDetailsResponse); // Return the post with comments
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error","Post does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody PostEditRequest postEditRequest) {
        if (postService.findPostById(postEditRequest.getPostID()).isPresent()) {
            postService.editPost(postEditRequest.getPostBody(), postEditRequest.getPostID() );
            return ResponseEntity.status(200).body("Post edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam Integer postID) {
        if (postService.findPostById(postID).isPresent()) {
            postService.deletePost(postID);
            return ResponseEntity.status(200).body("Post deleted");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }
}
