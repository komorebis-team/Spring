package com.itesm.komorebi.controllers;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getPerfil(){
        return categoryService.getAll();
    }

    @GetMapping("/name/{name}")
    public List<String> getTags(@PathVariable("name") String name){
        return categoryService.getCategoryTags(name);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping("/update")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
}
