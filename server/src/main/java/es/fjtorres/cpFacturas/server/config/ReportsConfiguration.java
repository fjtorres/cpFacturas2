package es.fjtorres.cpFacturas.server.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public final class ReportsConfiguration {

   public static enum Reports {
      
      INVOICE("reports/invoiceReport/invoice.jrxml", "reports/invoiceReport/invoice");
      
      private final String template;
      private final String i18nResourcePath;
      
      private Reports(String template, String i18nResourcePath) {
         this.template = template;
         this.i18nResourcePath = i18nResourcePath;
      }

      public final String getTemplate() {
         return template;
      }

      public final String getI18nResourcePath() {
         return i18nResourcePath;
      }
      
   }
   
   public static interface Parameters {
      String REPORTS_PATH = "REPORTS_PATH";
   }

   public static final String LOCATION = ".";
   public static final String REPORTS_LOCATION = "reports/";

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
