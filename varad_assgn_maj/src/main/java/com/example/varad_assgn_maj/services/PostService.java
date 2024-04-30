package com.example.varad_assgn_maj.services;

import com.example.varad_assgn_maj.model.Post;
import com.example.varad_assgn_maj.model.User;
import com.example.varad_assgn_maj.repository.PostRepository;
import com.example.varad_assgn_maj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import java.util.Optional;
import java.util.List;
import java.util.Date;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post createPost(String postBody, Integer userID) {
        User user = userRepository.findById(userID).orElse(null); // Retrieve the user
        Post newPost = new Post();
        newPost.setPostBody(postBody);
        newPost.setDate(new Date());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findPostById(Integer postID) {
        Optional<Post> postOptional = postRepository.findById(postID);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Hibernate.initialize(post.getComments());
            return Optional.of(post);
        }

        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserById(Integer userID) {
        return userRepository.findById(userID); // Find user by ID
    }

    @Transactional
    public void editPost (String newPostBody,Integer postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        Post post = postOptional.orElse(null);

        if (post != null) {
            post.setPostBody(newPostBody);
            postRepository.save(post);
        }
    }

    @Transactional
    public void deletePost(Integer postID) {
        if (postRepository.existsById(postID)) {
            postRepository.deleteById(postID);
        }
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPostsInReverseChronologicalOrder() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "postID"));
    }
}
