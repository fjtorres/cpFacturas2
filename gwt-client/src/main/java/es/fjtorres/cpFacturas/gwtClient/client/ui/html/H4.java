package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H4 extends AbstractHeading {

    public H4 () {
        super(HeadingElement.TAG_H4);
    }
    
    public H4(final String html) {
        this();
        setHTML(html);
    }
}
