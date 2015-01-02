package es.fjtorres.cpFacturas.common.dto;

public class InsurerDto extends AbstractDto<Long> {

    private static final long serialVersionUID = -3356756855905033999L;

    private Long id;

    private String code;

    private String name;

    private ContactDataDto contactData;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public ContactDataDto getContactData() {
        return contactData;
    }

    public void setContactData(ContactDataDto pContactData) {
        contactData = pContactData;
    }
}
