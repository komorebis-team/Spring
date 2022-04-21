package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "Recording")
public class Recording {

    @Id
    private RecordingKey recordingKey;

    private Integer duration;
    private Map<String,String> agent;
    private String category;
    private Set<String> tags;
    private Boolean succesfulOutcome;
    private String customer;
    private Map<String,String> notes;
    private String external_id;

    @DynamoDBHashKey
    public Integer getVideo_id() {
        if (recordingKey != null){
            return recordingKey.getVideoId();
        }
        return null;
    }
    public void setVideo_id(Integer video_id) {
        if (recordingKey == null){
            recordingKey = new RecordingKey();
        }
        recordingKey.setVideoId(video_id);
    }

    @DynamoDBRangeKey
    public String getTimestamp() {
        if (recordingKey != null){
            return recordingKey.getTimestamp();
        }
        return null;
    }
    public void setTimestamp(String timestamp) {
        if (recordingKey == null){
            recordingKey = new RecordingKey();
        }
        recordingKey.setTimestamp(timestamp);
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Map<String, String> getAgent() {
        return agent;
    }

    public void setAgent(Map<String, String> agent) {
        this.agent = agent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Boolean getSuccesfulOutcome() {
        return succesfulOutcome;
    }

    public void setSuccesfulOutcome(Boolean succesfulOutcome) {
        this.succesfulOutcome = succesfulOutcome;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Map<String,String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String,String> notes) {
        this.notes = notes;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }
}