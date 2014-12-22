package es.fjtorres.cpFacturas.gwtClient.client.ui.html;

import com.google.gwt.dom.client.HeadingElement;

public class H5 extends AbstractHeading {

    public H5 () {
        super(HeadingElement.TAG_H5);
    }
    
    public H5(final String html) {
        this();
        setHTML(html);
    }
}
