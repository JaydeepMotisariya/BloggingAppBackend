package com.riseup.bloggingapp.repositories;

import com.riseup.bloggingapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
