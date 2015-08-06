package es.fjtorres.cpFacturas.server.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public final class ReportsConfiguration {

   public static interface Parameters {
      String REPORTS_PATH = "REPORTS_PATH";
   }

   public static final String LOCATION = ".";
   public static final String REPORTS_LOCATION = "reports/";
   public static final String TEMPLATE_INVOICES = "reports/invoiceReport/invoice.jrxml";

   public static String getTemplatePath(final String template) {
      return Paths.get(getResourceFromClassPath(template)).toString();
   }

   public static String getReportsLocation() {
      return Paths.get(getResourceFromClassPath(REPORTS_LOCATION)).toString();
   }

   private static URI getResourceFromClassPath(final String resource) {
      try {
         return ReportsConfiguration.class.getClassLoader().getResource(resource).toURI();
      } catch (URISyntaxException e) {
         throw new RuntimeException(e);
      }
   }
}
