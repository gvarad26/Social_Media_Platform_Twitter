package com.example.varad_assgn_maj.repository;

import com.example.varad_assgn_maj.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    //List<Post> findAllByOrderByDateDesc(); // Fetch all posts ordered by date
    List<Post> findAll(Sort sort);
}
