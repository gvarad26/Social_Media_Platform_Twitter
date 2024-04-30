package com.example.varad_assgn_maj.controller;

import com.example.varad_assgn_maj.DTO.UserDTO;
import com.example.varad_assgn_maj.DTO.UserDTO.*;
import com.example.varad_assgn_maj.services.UserService;
import com.example.varad_assgn_maj.services.PostService;
import com.example.varad_assgn_maj.model.User;
import com.example.varad_assgn_maj.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO.UserSignupRequest userSignupRequest) {
        Optional<User> existingUser = userService.findUserByEmail(userSignupRequest.getEmail());
        if (existingUser.isPresent()) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

        }

        User newUser = new User();
        newUser.setEmail(userSignupRequest.getEmail());
        newUser.setName(userSignupRequest.getName());
        newUser.setPassword(userSignupRequest.getPassword());

        userService.createUser(newUser);

        return ResponseEntity.ok("Account Creation Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO.UserLoginRequest userLoginRequest) {
        Optional<User> user = userService.findUserByEmail(userLoginRequest.getEmail());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginRequest.getPassword())) {
                return ResponseEntity.status(200).body("Login Successful");
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("Error", "Username/Password Incorrect");
                return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);

            }
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);

        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam Integer userID) {
        Optional<User> user = userService.findUserById(userID);
        if (user.isPresent()) {
            UserDetailDTO userDetail = new UserDetailDTO(user.get());
            return ResponseEntity.status(200).body(userDetail);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(errorResponse);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserFeed() {
        List<Post> posts = postService.getAllPostsInReverseChronologicalOrder();
        List<PostWithComments> postResponses = posts.stream()
                .map(PostWithComments::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(postResponses);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDetailDTO> userDTOs = users.stream()
                .map(UserDetailDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(userDTOs);
    }
}
