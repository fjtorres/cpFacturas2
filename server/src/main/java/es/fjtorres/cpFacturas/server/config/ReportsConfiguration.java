package es.fjtorres.cpFacturas.server.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public final class ReportsConfiguration {

   public static enum Reports {
      
      INVOICE("invoiceReport", "invoice.jrxml", "invoice");
      
      private final String template;
      private final String i18nResource;
      private final String baseName;
      
      private Reports(final String pBaseName, String template, String i18nResource) {
         this.baseName = pBaseName;
         this.template = template;
         this.i18nResource = i18nResource;
      }

      public final String getTemplate() {
         return template;
      }

      public final String getI18nResource() {
         return i18nResource;
      }

      public String getBaseName() {
         return baseName;
      }
      
      public String getReportPath () {
         return REPORTS_LOCATION + getBaseName() + "/";
      }
      
      public String getTemplatePath() {
         return getReportPath() + getTemplate();
      }
      
      public String getI18nResourcePath() {
         return getReportPath() + getI18nResource();
      }
      
   }
   
   public static interface Parameters {
      String REPORTS_PATH = "REPORTS_PATH";
   }

   public static final String LOCATION = ".";
   public static final String REPORTS_LOCATION = "reports/";

   public static String getResourcePath(final String resource) {
      return Paths.get(getResourceFromClassPath(resource)).toString();
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
