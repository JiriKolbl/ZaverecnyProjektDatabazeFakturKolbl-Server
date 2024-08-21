package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class representing an invoice in the database.
 * This class uses the Lombok library to automatically generate getters and setters.
 * An invoice is linked with {@link PersonEntity} instances through Many-to-One relationships.
 */
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {
    /**
     * The unique identifier for the invoice, automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The invoice number, a mandatory attribute.
     */
    @Column(nullable = false)
    private int invoiceNumber;

    /**
     * The date the invoice was issued, a mandatory attribute.
     */
    @Column(nullable = false)
    private LocalDate  issued;

    /**
     * The due date for the invoice, a mandatory attribute.
     */
    @Column(nullable = false)
    private LocalDate dueDate;

    /**
     * The product name or description associated with the invoice, a mandatory attribute.
     */
    @Column(nullable = false)
    private String product;

    /**
     * The price of the product or service, a mandatory attribute.
     */
    @Column(nullable = false)
    private long price;

    /**
     * The value-added tax (VAT) rate applicable to the transaction, a mandatory attribute.
     */
    @Column(nullable = false)
    private int vat;

    /**
     * An optional note that can be attached to the invoice.
     */
    private String note;

    /**
     * The buyer of the service or product, represented as a {@link PersonEntity}.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    // @JoinColumn(name = "buyer_id") // Uncomment to specify the column name in the database.
    private PersonEntity buyer;

    /**
     * The seller of the service or product, represented as a {@link PersonEntity}.
     * This is a many-to-one relationship.
     */
    @ManyToOne
    // @JoinColumn(name = "seller_id") // Uncomment to specify the column name in the database.
    private PersonEntity seller;
}