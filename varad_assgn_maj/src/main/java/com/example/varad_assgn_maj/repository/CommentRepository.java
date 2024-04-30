package com.example.varad_assgn_maj.repository;

import com.example.varad_assgn_maj.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
