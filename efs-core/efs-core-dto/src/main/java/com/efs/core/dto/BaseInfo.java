package com.efs.core.dto;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 */
public class BaseInfo {

    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(readOnly = true)
    private DateTime createdDate;

    @ApiModelProperty(hidden = true, readOnly = true)
    private DateTime updatedDate;

    @ApiModelProperty(hidden = true, readOnly = true)
    private String createdBy;

    @ApiModelProperty(hidden = true, readOnly = true)
    private String updatedBy;

    @ApiModelProperty(value = "false")
    private boolean deleted;

    @Override
    public final String toString() {
        return this.toStringCreator().toString();
    }

    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("created", createdDate)
                .append("updated", createdDate)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
