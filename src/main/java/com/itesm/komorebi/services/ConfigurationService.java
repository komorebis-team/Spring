package com.itesm.komorebi.services;

import com.itesm.komorebi.models.Category;
import com.itesm.komorebi.models.Configuration;
import com.itesm.komorebi.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {
    @Autowired
    ConfigurationRepository configurationRepository;

    //CREATE

    //Retrieve
    public Optional<List<Category>> getAllCategories(){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(configuration.get().getCategories());
    }

    public Optional<Category> getCategory(String category){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return Optional.empty();
        }
        List<Category> categoryList = configuration.get().getCategories();
        for (Category c : categoryList){
            if (c.getCategoryName().equals(category)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public Optional<HashSet<String>> getAllTags(){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return Optional.empty();
        }
        HashSet<String> availableTags = new HashSet<String>();
        List<Category> categories = configuration.get().getCategories();
        for (Category category : categories){
            availableTags.addAll(category.getAvailableTags());
        }
        return Optional.of(availableTags);
    }

    public Optional<Configuration> getConfig(){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(configuration.get());
    }

    //UPDATE

    private Configuration update(Configuration configuration){
        return configurationRepository.save(configuration);
    }

    public Configuration addCategory(Category category){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return null;
        }
        List<Category> categoryList = configuration.get().getCategories();
        categoryList.add(category);
        configuration.get().setCategories(categoryList);
        return update(configuration.get());
    }

    public Configuration addTag(String category, String tag){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return null;
        }
        List<Category> categoryList = configuration.get().getCategories();
        List<String> tags = null;
        for (Category c : categoryList) {
            if (c.getCategoryName().equals(category)) {
                tags = c.getAvailableTags();
                tags.add(tag);
                c.setAvailableTags(tags);
            }
        }
        configuration.get().setCategories(categoryList);
        return update(configuration.get());
    }

    //DELETE
    public Configuration deleteTag(String category, String tag){
        Optional<Configuration> configuration = configurationRepository.findById(1);
        if (configuration.isEmpty()){
            return null;
        }
        List<Category> categoryList = configuration.get().getCategories();
        List<String> tags = null;
        for (Category c : categoryList){
            if (c.getCategoryName().equals(category)){
                tags = c.getAvailableTags();
                tags.remove(tag);
                c.setAvailableTags(tags);
            }
        }
        configuration.get().setCategories(categoryList);
        return update(configuration.get());
    }
}
