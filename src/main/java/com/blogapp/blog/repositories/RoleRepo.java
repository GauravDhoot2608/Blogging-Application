package com.blogapp.blog.repositories;

import com.blogapp.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role , Integer> {

}
