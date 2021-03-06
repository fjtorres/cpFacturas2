package es.fjtorres.cpFacturas.rest.api;

public final class NameTokens {

    public static final String AUTHENTICATION_PATH = "/authentication";

    public static final String AUTHENTICATION_LOGOUT_PATH = AUTHENTICATION_PATH + "/logout";

    public static final String CUSTOMERS_PATH = "/customers";
    
    public static final String ENUM_PATH = "/enum";

    public static final String INSURERS_PATH = "/insurers";

    public static final String INVOICES_PATH = "/invoices";
    
    public static final String INVOICES_EXPORT_PATH = INVOICES_PATH + "/export/";

    public static final String VEHICLES_PATH = "/vehicles";
    public static final String VEHICLES_BRAND_PATH = VEHICLES_PATH + "/brands";
    public static final String VEHICLES_MODEL_PATH = VEHICLES_PATH + "/models";

    public static final String PAGE_NUMBER = "page";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_SORT_FIELD = "sortField";
    public static final String PAGE_SORT_DIRECTION = "sortDirection";
}
