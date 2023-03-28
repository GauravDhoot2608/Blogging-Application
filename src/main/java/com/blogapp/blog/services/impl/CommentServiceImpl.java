package com.blogapp.blog.services.impl;

import com.blogapp.blog.entities.Comment;
import com.blogapp.blog.entities.Post;
import com.blogapp.blog.entities.User;
import com.blogapp.blog.exceptions.ResourceNotFoundException;
import com.blogapp.blog.payloads.CommentDto;
import com.blogapp.blog.repositories.CommentRepo;
import com.blogapp.blog.repositories.PostRepo;
import com.blogapp.blog.repositories.UserRepo;
import com.blogapp.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId , Integer postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , "user id" , userId));
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "post id" , postId));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        comment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "post id" , postId));
        List<Comment> comments = this.commentRepo.findByPost(post);
        List<CommentDto> commentDtos = comments.stream().map((comment) -> this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public List<CommentDto> getCommentsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","user id",userId));
        List<Comment> comments = this.commentRepo.findByUser(user);
        List<CommentDto> commentDtos = comments.stream().map((comment) -> this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);
    }
}
