package com.efs.core.mongo.model;

import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.event.AuditingEventListener;

import com.efs.core.mongo.support.BaseMongoEntityListener;

@EntityListeners({
    AuditingEventListener.class, //this sets the dates
    BaseMongoEntityListener.class //this sets createdby, updatedby
})
public class BaseMongoEntity {

    @Id
    private String id;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime updatedDate;

    private String createdBy;
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
