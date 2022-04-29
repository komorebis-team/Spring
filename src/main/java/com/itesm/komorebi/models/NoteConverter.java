package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Annotation;
import java.util.List;

public class NoteConverter implements DynamoDBTypeConverter<String, List<Note>>  {
    @Override
    public String convert(List<Note> object){
        List<Note> itemNote = (List<Note>) object;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try{
            jsonStr = objectMapper.writeValueAsString(itemNote);

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }

    @Override
    public List<Note> unconvert(String s){;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Note> notes = objectMapper.readValue(s, new TypeReference<List<Note>>(){});
            return notes;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
