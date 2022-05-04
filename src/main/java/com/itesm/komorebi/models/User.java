package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Set;

@DynamoDBTable(tableName = "User")
public class User {

    private String email;
    private String name;
    private String lastname;
    private String role;
    private boolean active;
    private String externalId;
    private String pwdHash;
    private String supervisor;
    private Set<String> subordinated;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @DynamoDBAttribute(attributeName = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @DynamoDBAttribute(attributeName = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @DynamoDBAttribute(attributeName = "externalId")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @DynamoDBAttribute(attributeName = "pwdHash")
    public String getPwdHash() {return pwdHash;}

    public void setPwdHash(String pwdHash) {this.pwdHash = pwdHash; }

    @DynamoDBAttribute(attributeName = "supervisor")
    public String getSupervisor() {return supervisor;}

    public void setSupervisor(String supervisor) {this.supervisor = supervisor;}

    @DynamoDBAttribute(attributeName = "subordinated")
    public Set<String> getSubordinated() {return subordinated;}

    public void setSubordinated(Set<String> subordinated) {this.subordinated = subordinated;}
}
