package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H2 extends AbstractHeading {

    public H2 () {
        super(HeadingElement.TAG_H2);
    }
    
    public H2(final String html) {
        this();
        setHTML(html);
    }
}
