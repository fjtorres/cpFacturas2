package es.fjtorres.cpFacturas.common.exception;

public class AppException extends Exception {

    private static final long serialVersionUID = -5194453961613355216L;

    public AppException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }

    public AppException(String pMessage) {
        super(pMessage);
    }
    
    public AppException () {
        
    }

}
