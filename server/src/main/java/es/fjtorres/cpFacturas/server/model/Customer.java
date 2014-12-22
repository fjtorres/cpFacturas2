package es.fjtorres.cpFacturas.server.model;

import java.util.Objects;

public class Customer extends AbstractEntity<Long> {

    /**
     * 
     */
    private static final long serialVersionUID = -854986097302149903L;

    private Long id;

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
        } else if (pObj instanceof Customer) {
            final Customer other = (Customer) pObj;
            isEquals = Objects.equals(getCode(), other.getCode());
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long pId) {
        this.id = pId;
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
