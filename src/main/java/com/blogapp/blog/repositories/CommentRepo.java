package com.blogapp.blog.repositories;

import com.blogapp.blog.entities.Comment;
import com.blogapp.blog.entities.Post;
import com.blogapp.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
