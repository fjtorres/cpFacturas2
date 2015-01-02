package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {

    private static final long serialVersionUID = 8288614049783366029L;

    private String text;

    private MessageStatus status;

    public Message() {

    }

    public Message(String pText, MessageStatus pStatus) {
        text = pText;
        status = pStatus;
    }

    public String getText() {
        return text;
    }

    public void setText(String pText) {
        text = pText;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus pStatus) {
        status = pStatus;
    }

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof Message) {
            final Message other = (Message) pObj;
            isEquals = Objects.equals(getStatus(), other.getStatus())
                    && Objects.equals(getText(), other.getText());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getText());
    }

}
