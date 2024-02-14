package com.riseup.bloggingapp.repositories;

import com.riseup.bloggingapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
