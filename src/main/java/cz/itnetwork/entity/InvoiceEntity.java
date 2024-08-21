package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Třída reprezentující entitu faktury (Invoice) v databázi.
 * Tato třída je mapována na tabulku s názvem "invoice".
 */
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    /**
     * Jedinečný identifikátor faktury.
     * Hodnota je automaticky generována pomocí identity strategie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Číslo faktury, které je unikátní a nesmí být null.
     */
    @Column(nullable = false)
    private int invoiceNumber;

    /**
     * Datum vystavení faktury. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private LocalDate issued;

    /**
     * Datum splatnosti faktury. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private LocalDate dueDate;

    /**
     * Název produktu nebo služby, která je fakturována.
     * Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String product;

    /**
     * Cena produktu nebo služby v dlouhém formátu (např. v haléřích, centech, apod.).
     * Tento údaj je povinný.
     */
    @Column(nullable = false)
    private long price;

    /**
     * Hodnota DPH (VAT) v procentech.
     * Tento údaj je povinný.
     */
    @Column(nullable = false)
    private int vat;

    /**
     * Volitelná poznámka k faktuře.
     */
    private String note;

    /**
     * Entita reprezentující kupujícího (buyer) v této transakci.
     * Kupující je propojen s fakturou pomocí vztahu @ManyToOne.
     */
    @ManyToOne
    private PersonEntity buyer;

    /**
     * Entita reprezentující prodávajícího (seller) v této transakci.
     * Prodávající je propojen s fakturou pomocí vztahu @ManyToOne.
     */
    @ManyToOne
    private PersonEntity seller;
}