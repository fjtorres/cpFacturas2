package es.fjtorres.cpFacturas.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

   private final Logger log = LoggerFactory.getLogger(SimpleCorsFilter.class);

   public SimpleCorsFilter() {
      log.info("SimpleCORSFilter init");
   }

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
         throws IOException, ServletException {
      HttpServletResponse response = (HttpServletResponse) res;
      HttpServletRequest request = (HttpServletRequest) req;
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with, X-Auth-Token");
      response.setHeader("Access-Control-Max-Age", "3600");
      if (request.getMethod() != "OPTIONS") {
         chain.doFilter(req, res);
      } else {
      }
   }

   public void init(FilterConfig filterConfig) {
   }

   public void destroy() {
   }

}