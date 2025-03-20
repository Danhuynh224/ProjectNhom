package com.example.api.controller;

import com.example.api.entity.Category;
import com.example.api.service.CategoryService;
import com.example.api.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(path = "/all-categories")
    public ResponseEntity<?> getCategory() {
        List<Category> categories = categoryService.findAll();
        if (categories == null || categories.isEmpty()) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Category list is empty",
                    "No categories found"
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
