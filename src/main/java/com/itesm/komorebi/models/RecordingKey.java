package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import java.io.Serializable;

public class RecordingKey implements Serializable{
    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey
    private String agentId;

    @DynamoDBRangeKey
    private String timestamp;

    public String getAgentId() {return agentId;}

    public void setAgentId(String agentId) {this.agentId = agentId;}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}


