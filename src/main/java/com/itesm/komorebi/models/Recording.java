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

    public RecordingKey recordingKeyGet() {
        return recordingKey;
    }

    private Integer duration;
    private Map<String,String> agent;
    private String category;
    private Set<String> tags;
    private Boolean successfulOutcome;
    private String customer;
    private List<Note> notes;
    private String externalId;

    @DynamoDBHashKey(attributeName = "agentId")
    public String getAgentId() {
        if (recordingKey != null){
            return recordingKey.getAgentId();
        }
        return null;
    }
    public void setAgentId(String agentId) {
        if (recordingKey == null){
            recordingKey = new RecordingKey();
        }
        recordingKey.setAgentId(agentId);
    }

    @DynamoDBRangeKey(attributeName = "timestamp")
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

    @DynamoDBAttribute(attributeName = "duration")
    public Integer getDuration() {return duration;}

    public void setDuration(Integer duration) {this.duration = duration;}

    @DynamoDBAttribute(attributeName = "agent")
    public Map<String, String> getAgent() {return agent;}

    public void setAgent(Map<String, String> agent) {this.agent = agent;}

    @DynamoDBAttribute(attributeName = "category")
    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    @DynamoDBAttribute(attributeName = "tags")
    public Set<String> getTags() {return tags;}

    public void setTags(Set<String> tags) {this.tags = tags;}

    @DynamoDBAttribute(attributeName = "successfulOutcome")
    public Boolean getSuccessfulOutcome() {return successfulOutcome;}

    public void setSuccessfulOutcome(Boolean successfulOutcome) {this.successfulOutcome = successfulOutcome;}

    @DynamoDBAttribute(attributeName = "customer")
    public String getCustomer() {return customer;}

    public void setCustomer(String customer) {this.customer = customer;}

    @DynamoDBAttribute(attributeName = "notes")
    @DynamoDBTypeConverted(converter = NoteConverter.class)
    public List<Note> getNotes() {return notes;}

    public void setNotes(List<Note> notes) {this.notes = notes;}

    @DynamoDBAttribute(attributeName = "externalId")
    public String getExternalId() {return externalId;}

    public void setExternalId(String externalId) {this.externalId = externalId;}

}