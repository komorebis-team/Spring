package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public List<String> getCategoryTags(String category){
        return categoryRepository.getCategoryTags(category);
    }

    public Category addCategory(Category category){
        return categoryRepository.addCategory(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.updateCategory(category);
    }
}
