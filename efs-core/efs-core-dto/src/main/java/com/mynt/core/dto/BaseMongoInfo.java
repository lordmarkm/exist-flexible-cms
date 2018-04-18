package com.mynt.core.dto;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 */
public class BaseMongoInfo {

    @ApiModelProperty(value = "null")
    private String id;

    @ApiModelProperty(readOnly = true, hidden = true)
    private Long version;

    @ApiModelProperty(value = "false")
    private boolean deleted;

    @ApiModelProperty(readOnly = true, hidden = true)
    private DateTime createdDate;

    @ApiModelProperty(readOnly = true, hidden = true)
    private DateTime updatedDate;

    @ApiModelProperty(readOnly = true, hidden = true)
    private String createdBy;

    @ApiModelProperty(readOnly = true, hidden = true)
    private String updatedBy;

    @Override
    public final String toString() {
        return this.toStringCreator().toString();
    }

    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("version", version)
                .append("created", createdDate)
                .append("created by", createdBy)
                .append("updated", createdDate)
                .append("updated by", updatedBy)
                .append("deleted", deleted);
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

    public boolean getDeleted() {
        return deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
