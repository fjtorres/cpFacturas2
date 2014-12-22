package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H3 extends AbstractHeading {

    public H3 () {
        super(HeadingElement.TAG_H3);
    }
    
    public H3(final String html) {
        this();
        setHTML(html);
    }
}
