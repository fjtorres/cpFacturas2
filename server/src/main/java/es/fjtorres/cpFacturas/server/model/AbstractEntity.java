package es.fjtorres.cpFacturas.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.google.common.base.MoreObjects;

@MappedSuperclass
@EntityListeners({
    AuditEntityListener.class
})
public abstract class AbstractEntity<Id extends Serializable> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2201504423816671630L;
    
    public static final Long DEFAULT_ID = -1L;

    @Version
    private long version;

    @Column(name = "AUDIT_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "AUDIT_LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    public abstract Id getId();

    public abstract void setId(Id id);

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof AbstractEntity) {
            final AbstractEntity<?> other = (AbstractEntity<?>) pObj;
            isEquals = Objects.equals(getId(), other.getId());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("ID", getId()).toString();
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long pVersion) {
        version = pVersion;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date pCreationDate) {
        creationDate = pCreationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date pLastUpdateDate) {
        lastUpdateDate = pLastUpdateDate;
    }
}
