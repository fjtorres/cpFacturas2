package es.fjtorres.cpFacturas.gwtClient.client.ui.widget;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public class EnumTranslator {
    
    private ConstantsWithLookup constants;

    public EnumTranslator(ConstantsWithLookup constants) {
        this.constants = constants;
    }

    public String getText(Enum<?> e) {
        String lookupKey = e.getClass().getName() + "." + e.name();
        lookupKey = lookupKey.replace(".", "_");
        lookupKey = lookupKey.replace("$", "_");
        return constants.getString(lookupKey);
    }
}