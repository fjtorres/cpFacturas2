package es.fjtorres.cpFacturas.common.dto;

import java.util.Objects;

public class CustomerDto extends AbstractDto {

    private static final long serialVersionUID = 4003447769673727730L;

    private String code;

    private String firstName;

    private String lastName;

    @Override
    public boolean equals(Object pObj) {
        boolean isEquals = false;

        if (this == pObj) {
            isEquals = true;
        } else if (pObj == null) {
            isEquals = false;
        } else if (pObj instanceof CustomerDto) {
            final CustomerDto other = (CustomerDto) pObj;
            isEquals = Objects.equals(getCode(), other.getCode());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }

}
