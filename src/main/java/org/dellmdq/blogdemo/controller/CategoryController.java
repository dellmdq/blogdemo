package org.dellmdq.blogdemo.controller;


import org.dellmdq.blogdemo.entity.Category;
import org.dellmdq.blogdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> findAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public Category findById(@PathVariable int categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/title/{title}")
    public Category findById(@PathVariable String title){
        return categoryService.getCategoryByTitle(title);
    }

    @PostMapping
    public Category add(@RequestBody Category category){
        return categoryService.save(category);
    }



}
