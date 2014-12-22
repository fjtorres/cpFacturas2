package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H6 extends AbstractHeading {

    public H6 () {
        super(HeadingElement.TAG_H6);
    }
    
    public H6(final String html) {
        this();
        setHTML(html);
    }
}
