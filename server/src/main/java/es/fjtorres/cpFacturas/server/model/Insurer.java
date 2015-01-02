package es.fjtorres.cpFacturas.server.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INSURER")
public class Insurer extends AbstractEntity<Long> {

    private static final long serialVersionUID = -3356756855905033999L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSURER_SEQ")
    @SequenceGenerator(name = "INSURER_SEQ", sequenceName = "INSURER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CODE", length = 100, nullable = false, unique = true)
    private String code;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Embedded
    private ContactData contactData;

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

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData pContactData) {
        contactData = pContactData;
    }
}
