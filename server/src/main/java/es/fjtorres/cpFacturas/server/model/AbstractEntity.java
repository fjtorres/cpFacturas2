package es.fjtorres.cpFacturas.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.google.common.base.MoreObjects;

public abstract class AbstractEntity<Id extends Serializable> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2201504423816671630L;

    private long version;

    private Date creationDate;

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
        return MoreObjects.toStringHelper(this).toString();
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
