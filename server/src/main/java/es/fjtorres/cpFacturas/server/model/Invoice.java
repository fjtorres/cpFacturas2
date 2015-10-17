package es.fjtorres.cpFacturas.server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.fjtorres.cpFacturas.common.InvoiceState;

@Entity
@Table(name = "INVOICES")
public class Invoice extends AbstractEntity<Long> {

    private static final long serialVersionUID = 2190612963674804427L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_SEQ")
    @SequenceGenerator(name = "INVOICE_SEQ", sequenceName = "INVOICE_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false, length = 25)
    private InvoiceState state = InvoiceState.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceLine> lines;

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> pLines) {
        lines = pLines;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle pVehicle) {
        vehicle = pVehicle;
    }
}
