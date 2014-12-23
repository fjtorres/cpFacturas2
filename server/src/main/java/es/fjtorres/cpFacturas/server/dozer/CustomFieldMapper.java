package es.fjtorres.cpFacturas.server.dozer;

import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;

public class CustomFieldMapper implements org.dozer.CustomFieldMapper {
   @Override
   public boolean mapField(final Object source, Object destination,
         final Object sourceFieldValue, final ClassMap classMap,
         final FieldMap fieldMapping) {

      // // Check if field is a Hibernate PersistentSet
      // if (!(sourceFieldValue instanceof PersistentSet)) {
      // // Allow dozer to map as normal
      // return false;
      // }
      //
      // // Check if field is already initialized
      // if (((PersistentSet) sourceFieldValue).wasInitialized()) {
      // // Allow dozer to map as normal
      // return false;
      // }

      // Set destination to null, and tell dozer that the field is mapped
      destination = null;
      return true;
   }
}
