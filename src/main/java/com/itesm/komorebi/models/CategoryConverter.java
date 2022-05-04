package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class CategoryConverter implements DynamoDBTypeConverter<String, List<Category>> {

    @Override
    public String convert(List<Category> categories) {
        List<Category> itemCategory = (List<Category>) categories;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try{
            jsonStr = objectMapper.writeValueAsString(itemCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }

    @Override
    public List<Category> unconvert(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Category> categories = objectMapper.readValue(s, new TypeReference<List<Category>>(){});
            return categories;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
