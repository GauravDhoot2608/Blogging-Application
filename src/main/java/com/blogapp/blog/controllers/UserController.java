package com.blogapp.blog.controllers;

import com.blogapp.blog.payloads.ApiResponse;
import com.blogapp.blog.payloads.UserDto;
import com.blogapp.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST -> Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto , HttpStatus.CREATED);
    }

    // PUT  -> Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto , userId);
        return ResponseEntity.ok(updatedUser);
    }

    // ADMIN CAN ONLY DELETE THE USER
    // DELETE -> Delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>  deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully" , true) , HttpStatus.OK);
    }

    // GET -> Get the user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>  getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>  getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}
