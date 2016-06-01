package es.fjtorres.cpFacturas.server.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

   public ApplicationConfig() {
      packages("es.fjtorres.cpFacturas.server.api.impl",
            "es.fjtorres.cpFacturas.server.config");

      // SLF4JBridgeHandler.removeHandlersForRootLogger();
      // SLF4JBridgeHandler.install();

      // register(new LoggingFilter(LogManager.getLogManager().getLogger(""),
      // true));

      property(
            "jersey.config.beanValidation.enableOutputValidationErrorEntity.server",
            true);
   }
}
