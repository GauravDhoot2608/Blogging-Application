package com.blogapp.blog.services;

import com.blogapp.blog.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    // create comment
    CommentDto createComment(CommentDto commentDto ,Integer userId ,Integer postId);

    // update comment
    CommentDto updateComment(CommentDto commentDto , Integer commentId);

    // get all comment by post
    List<CommentDto>  getCommentsByPost(Integer postId);

    List<CommentDto>  getCommentsByUser(Integer userId);

    // delete comment
    void deleteComment(Integer commentId);
}
