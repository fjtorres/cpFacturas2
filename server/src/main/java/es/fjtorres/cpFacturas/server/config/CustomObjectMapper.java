package es.fjtorres.cpFacturas.server.config;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper implements ContextResolver<ObjectMapper> {

   final ObjectMapper defaultObjectMapper;

   public CustomObjectMapper() {
      defaultObjectMapper = createDefaultMapper();
   }

   @Override
   public ObjectMapper getContext(final Class<?> type) {
      return defaultObjectMapper;
   }

   private static ObjectMapper createDefaultMapper() {
      final ObjectMapper result = new ObjectMapper();
      result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return result;
   }

}
