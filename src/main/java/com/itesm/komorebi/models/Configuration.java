package com.itesm.komorebi.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.List;

@DynamoDBTable(tableName = "Configuration")
public class Configuration {
    private Integer configId;
    private boolean encryption;
    private boolean allowSharing;
    private List<Category> categories;

    @DynamoDBHashKey(attributeName = "configId")
    public Integer getConfigId() {return configId;}

    public void setConfigId(Integer configId) {this.configId = configId;}

    @DynamoDBAttribute(attributeName = "encryption")
    public boolean isEncryption() {return encryption;}

    public void setEncryption(boolean encryption) {this.encryption = encryption;}

    @DynamoDBAttribute(attributeName = "allowSharing")
    public boolean isAllowSharing() {return allowSharing;}

    public void setAllowSharing(boolean allowSharing) {this.allowSharing = allowSharing;}

    @DynamoDBAttribute(attributeName = "categories")
    @DynamoDBTypeConverted(converter = CategoryConverter.class)
    public List<Category> getCategories() {return categories;}

    public void setCategories(List<Category> categories) {this.categories = categories;}
}
