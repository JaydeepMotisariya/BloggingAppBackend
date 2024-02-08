package com.riseup.bloggingapp.repositories;

import com.riseup.bloggingapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
