package es.fjtorres.cpFacturas.server.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * JPA Listener to check audit properties (creationDate and modificationDate).
 * 
 * @author fjtorres
 *
 * @param <T>
 *            Entity model type.
 */
public class AuditEntityListener<T extends AbstractEntity<?>> {

    /**
     * Set creationDate and modificationDate in model before persit event.
     * 
     * @param model
     *            Entity model instance.
     */
    @PrePersist
    public void prePersit(final T model) {
        if (model != null) {
            final Date currentDate = new Date();
            model.setCreationDate(currentDate);
            model.setLastUpdateDate(currentDate);
        }
    }

    /**
     * Update modificationDate in model before update event.
     * 
     * @param model
     *            Entity model instance.
     */
    @PreUpdate
    public void preUpdate(final T model) {
        if (model != null) {
            model.setLastUpdateDate(new Date());
        }
    }
}