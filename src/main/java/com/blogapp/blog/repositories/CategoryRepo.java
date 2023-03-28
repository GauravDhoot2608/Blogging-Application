package com.blogapp.blog.repositories;

import com.blogapp.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {

}
