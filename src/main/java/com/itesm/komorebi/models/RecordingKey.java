package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import java.io.Serializable;


public class RecordingKey implements Serializable{
    private static final long serialVersionUID = 1L;

    @DynamoDBRangeKey
    private String timestamp;

    @DynamoDBHashKey
    private Integer videoId;

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}


