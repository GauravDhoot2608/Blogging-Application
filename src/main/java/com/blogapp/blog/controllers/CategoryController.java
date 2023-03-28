package com.blogapp.blog.controllers;

import com.blogapp.blog.payloads.ApiResponse;
import com.blogapp.blog.payloads.CategoryDto;
import com.blogapp.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCat = this.categoryService.createCategory(categoryDto);
         return new ResponseEntity<>(createdCat , HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable Integer catId){
        CategoryDto updatedCat = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(updatedCat , HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully" , true) , HttpStatus.OK);
    }

    // get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        return ResponseEntity.ok(this.categoryService.getCategory(catId));
    }

    // get all
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
}
