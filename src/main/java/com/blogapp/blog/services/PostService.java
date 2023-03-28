package com.blogapp.blog.services;


import com.blogapp.blog.entities.Post;
import com.blogapp.blog.payloads.PostDto;
import com.blogapp.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // create post
    PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);

    // update post
    PostDto updatePost(PostDto postDto , Integer postId);

    // delete post
    void deletePost(Integer postId);

    // get all post
    PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortDir);

    // get post by id
    PostDto getPostById(Integer postId);

    // get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    // get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
