package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {

   private static final long serialVersionUID = 8288614049783366029L;

   private String[] messages;

   private MessageStatus status;

   public Message() {

   }

   public Message(final MessageStatus pStatus, final String pMessage) {
      messages = new String[] {
         pMessage
      };
      status = pStatus;
   }

   public Message(final MessageStatus pStatus, final String... pMessages) {
      messages = pMessages;
      status = pStatus;
   }

   public MessageStatus getStatus() {
      return status;
   }

   public void setStatus(final MessageStatus pStatus) {
      status = pStatus;
   }

   @Override
   public boolean equals(final Object pObj) {
      boolean isEquals = false;

      if (this == pObj) {
         isEquals = true;
      } else if (pObj == null) {
         isEquals = false;
      } else if (pObj instanceof Message) {
         final Message other = (Message) pObj;
         isEquals = Objects.equals(getStatus(), other.getStatus())
               && Objects.equals(getMessages(), other.getMessages());
      }
      return isEquals;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getStatus(), getMessages());
   }

   public String[] getMessages() {
      return messages;
   }

   public void setMessages(final String[] messages) {
      this.messages = messages;
   }

}
