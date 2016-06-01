package es.fjtorres.cpFacturas.server;

import java.io.IOException;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import es.fjtorres.cpFacturas.server.dozer.CustomFieldMapper;

@SpringBootApplication
@ComponentScan(basePackages={"es.fjtorres.jasperReport.service", "es.fjtorres.cpFacturas.server"})
@ImportResource("classpath:applicationContext*.xml")
public class Application {

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
   
   @Bean
   public CustomFieldMapper customFieldMapper () {
      return new CustomFieldMapper();
   }
   
   @Bean
   public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(final ApplicationContext context) throws IOException {
      final DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
      factoryBean.setMappingFiles(context.getResources("classpath*:/dozer/*mapping.xml"));
      return factoryBean;
   }
}
