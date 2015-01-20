package es.fjtorres.cpFacturas.common.exception;

import java.util.List;

public class ValidationException extends AppException {

    /**
     * The serialVersionUID of class.
     */
    private static final long serialVersionUID = 7928089570418890043L;

    private List<String> errors;

    public ValidationException() {
        
    }
    
    public ValidationException(final List<String> pErrors) {
        this.errors = pErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> pErrors) {
        errors = pErrors;
    }
}
