package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import org.gwtbootstrap3.client.ui.base.mixin.HTMLMixin;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

public abstract class AbstractHeading extends HTMLPanel {

    private final HTMLMixin<AbstractHeading> textMixin = new HTMLMixin<AbstractHeading>(this);

    public AbstractHeading(final String tag) {
        super(tag, "");
    }

    public void setText(final String text) {
        textMixin.setText(text);
    }

    public String getText() {
        return textMixin.getText();
    }
    
    public String getHTML() {
        return textMixin.getHTML();
    }

    public void setHTML(final String html) {
        textMixin.setHTML(html);
    }
}
