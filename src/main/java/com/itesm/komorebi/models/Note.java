package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import java.util.List;

@DynamoDBDocument
public class Note {
    private String authorId;
    private String date;
    private List<String> tags;

    public Note(){;}
    public Note(String authorId, String date, List<String> tags) {
        this.authorId = authorId;
        this.date = date;
        this.tags = tags;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
