package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H1 extends AbstractHeading {

    public H1 () {
        super(HeadingElement.TAG_H1);
    }
    
    public H1(final String html) {
        this();
        setHTML(html);
    }
}
