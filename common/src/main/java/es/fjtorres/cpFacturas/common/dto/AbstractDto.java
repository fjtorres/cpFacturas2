package es.fjtorres.cpFacturas.common.dto;

import java.io.Serializable;
import java.util.Objects;

import com.google.common.base.MoreObjects;

public abstract class AbstractDto implements Serializable {

    private static final long serialVersionUID = -3429267552152098675L;

    private Long id;

    private long version;

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof AbstractDto) {
            final AbstractDto other = (AbstractDto) pObj;
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

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long pVersion) {
        version = pVersion;
    }
}
