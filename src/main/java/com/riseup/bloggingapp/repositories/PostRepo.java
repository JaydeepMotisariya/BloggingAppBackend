package com.riseup.bloggingapp.repositories;

import com.riseup.bloggingapp.entities.Category;
import com.riseup.bloggingapp.entities.Post;
import com.riseup.bloggingapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
