package es.fjtorres.cpFacturas.server.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

   public ApplicationConfig() {
      packages("es.fjtorres.cpFacturas.server.api.impl",
            "es.fjtorres.cpFacturas.server.config",
            "es.fjtorres.cpFacturas.server.filter");

      // SLF4JBridgeHandler.removeHandlersForRootLogger();
      // SLF4JBridgeHandler.install();

      // register(new LoggingFilter(LogManager.getLogManager().getLogger(""),
      // true));

      property(
            "jersey.config.beanValidation.enableOutputValidationErrorEntity.server",
            true);
   }
}
