package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.Category;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryRepository {

    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();

        List<String> tags1 = Arrays.asList("Model A", "Model B", "Model C");
        categories.add(new Category("Sales", tags1));

        List<String> tags2 = Arrays.asList("Angry client", "Solved Problem");
        categories.add(new Category("Troubleshooting", tags2));

        return categories;
    }

    public List<String> getCategoryTags(String category){
        List<Category> categories = this.findAll();
        for (Category c: categories){
            if (c.getCategoryName().equals(category)){
                return c.getAvailableTags();
            }
        }
        return null;
    }

    public Category getCategoryByName(String category){
        List<Category> categories = this.findAll();
        for (Category c: categories){
            if (c.getCategoryName().equals(category)){
                return c;
            }
        }
        return null;
    }

    public Category addCategory(Category category){
        return category;
    }

    public Category updateCategory(Category category){
        return category;
    }
}
