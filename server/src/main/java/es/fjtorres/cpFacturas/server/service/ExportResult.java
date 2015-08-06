package es.fjtorres.cpFacturas.server.service;

import java.io.Serializable;

public class ExportResult implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 233819647457099384L;

   private final String filename;

   private final byte[] content;

   private final String mediaType;

   /**
    * @param pFilename
    *           Filename
    * @param pContent
    *           File content.
    * @param pMediaType
    *           MediaType for file download.
    */
   public ExportResult(String pFilename, byte[] pContent, final String pMediaType) {
      filename = pFilename;
      content = pContent;
      this.mediaType = pMediaType;
   }

   public String getFilename() {
      return filename;
   }

   public byte[] getContent() {
      return content;
   }

   public String getMediaType() {
      return mediaType;
   }

}
