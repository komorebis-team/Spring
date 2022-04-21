package com.itesm.komorebi.models;

import java.util.Date;
import java.util.UUID;

public class Note {
    private String authorId;
    private Date date;
    private String content;
    private String recordingId;

    public Note(){}

    public Note(String authorId, Date date, String content, UUID recordingId) {
        this.authorId = authorId;
        this.date = date;
        this.content = content;
        this.recordingId = recordingId.toString();
    }

    public String getAuthor() {
        return authorId;
    }

    public void setAuthor(String author) {
        this.authorId = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getRecordingId() {
        return UUID.fromString(recordingId);
    }
    public void setRecordingId(UUID recordingId) {
        this.recordingId = recordingId.toString();
    }
}
