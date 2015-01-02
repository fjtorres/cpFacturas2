package es.fjtorres.cpFacturas.common.dto;

public class InvoiceLineDto extends AbstractDto<Long> {

    private static final long serialVersionUID = -5260477311368504991L;

    private Long id;

    private Double price;

    private Double amount;

    private Float taxRate;

    private Float discount;

    private String description;

    public Double getTotalWithTaxRate() {
        Double total = 0D;
        if (getPrice() != null && getAmount() != null) {
            total = getPriceWithTaxRate() * getAmount();
        }
        return total;
    }

    public Double getTotalWithoutTaxRate() {
        Double total = 0D;
        if (getPrice() != null && getAmount() != null) {
            total = getPrice() * getAmount();
        }
        return total;
    }

    public Double getTaxRateAmount() {
        Double total = 0D;
        if (getPrice() != null && getTaxRate() != null) {
            total = (getPrice() * getTaxRate()) / 100;
        }
        return total;
    }

    public Double getPriceWithTaxRate() {
        Double priceWithTaxRate = 0D;
        if (getPrice() != null) {
            priceWithTaxRate = getPrice() + getTaxRateAmount();
        }
        return priceWithTaxRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double pPrice) {
        price = pPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double pAmount) {
        amount = pAmount;
    }

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float pTaxRate) {
        taxRate = pTaxRate;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float pDiscount) {
        discount = pDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }
}
