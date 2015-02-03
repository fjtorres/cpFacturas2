package es.fjtorres.cpFacturas.server.filter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.glassfish.jersey.message.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResponseLogFilter implements ContainerResponseFilter,
      ContainerRequestFilter, WriterInterceptor {

   private static final Logger LOGGER = LoggerFactory
         .getLogger(ResponseLogFilter.class);

   private static final String ENTITY_LOGGER_PROPERTY = ResponseLogFilter.class
         .getName()
         + ".entityLogger";
   private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;

   @Override
   public void filter(final ContainerRequestContext requestContext,
         final ContainerResponseContext responseContext) throws IOException {
      if (responseContext.hasEntity()) {
         final StringBuilder sb = new StringBuilder();
         final OutputStream stream = new LoggingStream(sb, responseContext
               .getEntityStream());
         responseContext.setEntityStream(stream);
         requestContext.setProperty(ENTITY_LOGGER_PROPERTY, stream);
      }
   }

   @Override
   public void filter(final ContainerRequestContext requestContext)
         throws IOException {
      if (requestContext.hasEntity()) {
         final StringBuilder sb = new StringBuilder();
         logInboundEntity(sb, requestContext.getEntityStream(), MessageUtils
               .getCharset(requestContext.getMediaType()));
         LOGGER.debug(sb.toString());
      }
   }

   @Override
   public void aroundWriteTo(
         final WriterInterceptorContext writerInterceptorContext)
         throws IOException, WebApplicationException {
      final LoggingStream stream = (LoggingStream) writerInterceptorContext
            .getProperty(ENTITY_LOGGER_PROPERTY);
      writerInterceptorContext.proceed();
      if (stream != null) {
         LOGGER.debug(stream
               .getStringBuilder(
                     MessageUtils.getCharset(writerInterceptorContext
                           .getMediaType())).toString());
      }
   }

   private InputStream logInboundEntity(final StringBuilder b,
         InputStream stream, final Charset charset) throws IOException {
      if (!stream.markSupported()) {
         stream = new BufferedInputStream(stream);
      }
      stream.mark(DEFAULT_MAX_ENTITY_SIZE + 1);
      final byte[] entity = new byte[DEFAULT_MAX_ENTITY_SIZE + 1];
      final int entitySize = stream.read(entity);
      b.append(new String(entity, 0, Math.min(entitySize,
            DEFAULT_MAX_ENTITY_SIZE), charset));
      if (entitySize > DEFAULT_MAX_ENTITY_SIZE) {
         b.append("...more...");
      }
      b.append('\n');
      stream.reset();
      return stream;
   }

   private class LoggingStream extends OutputStream {
      private final StringBuilder b;
      private final OutputStream inner;
      private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

      LoggingStream(final StringBuilder b, final OutputStream inner) {
         this.b = b;
         this.inner = inner;
      }

      StringBuilder getStringBuilder(final Charset charset) {
         // write entity to the builder
         final byte[] entity = baos.toByteArray();

         b.append(new String(entity, 0, Math.min(entity.length,
               DEFAULT_MAX_ENTITY_SIZE), charset));
         if (entity.length > DEFAULT_MAX_ENTITY_SIZE) {
            b.append("...more...");
         }
         b.append('\n');

         return b;
      }

      @Override
      public void write(final int i) throws IOException {
         if (baos.size() <= DEFAULT_MAX_ENTITY_SIZE) {
            baos.write(i);
         }
         inner.write(i);
      }
   }
}
