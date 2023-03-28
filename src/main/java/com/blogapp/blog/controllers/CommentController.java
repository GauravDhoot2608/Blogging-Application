package com.blogapp.blog.controllers;

import com.blogapp.blog.payloads.ApiResponse;
import com.blogapp.blog.payloads.CommentDto;
import com.blogapp.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment ,
                                                    @PathVariable Integer userId ,
                                                    @PathVariable Integer postId){
        CommentDto commentDto = this.commentService.createComment(comment  , userId , postId);
        return new ResponseEntity<>(commentDto , HttpStatus.CREATED);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> updatedComment(@RequestBody CommentDto commentDto , @PathVariable Integer commentId){
        CommentDto updatedComment = this.commentService.updateComment(commentDto,commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Integer postId){
        List<CommentDto> commentDtos = this.commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

    @GetMapping("/comments/user/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Integer userId){
        List<CommentDto> commentDtos = this.commentService.getCommentsByUser(userId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully !!",true) , HttpStatus.OK);
    }


}
