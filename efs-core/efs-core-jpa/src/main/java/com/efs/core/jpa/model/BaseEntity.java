package com.efs.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.mynt.core.util.AuthenticationUtil;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by", updatable = false, length = 250)
    private String createdBy;

    @Column(name = "created_date", updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Column(name = "updated_by", length = 250)
    private String updatedBy;

    @Column(name = "updated_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDate;

    @Column(name = "deleted")
    @Type(type = "yes_no")
    private Boolean deleted = false;

    @Column(name = "version")
    private int version = 1;

    @PrePersist
    public void create() {
        this.createdDate = DateTime.now();
        this.updatedDate = DateTime.now();
        this.createdBy = AuthenticationUtil.getLoggedInUsername();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
    }

    @PreUpdate
    public void update() {
        this.updatedDate = DateTime.now();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
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

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (updatedDate == null) {
            if (other.updatedDate != null)
                return false;
        } else if (!updatedDate.equals(other.updatedDate))
            return false;
        return true;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}