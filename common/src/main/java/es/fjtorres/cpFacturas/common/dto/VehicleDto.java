package es.fjtorres.cpFacturas.common.dto;

public class VehicleDto extends AbstractDto<Long> {

    private static final long serialVersionUID = 5179390791312041743L;

    private Long id;

    private String registration;

    private CustomerDto customer;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String pRegistration) {
        registration = pRegistration;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto pCustomer) {
        customer = pCustomer;
    }

}
