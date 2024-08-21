/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.entity;

import cz.itnetwork.constant.Countries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Třída reprezentující entitu osoby (Person) v databázi.
 * Tato třída je mapována na tabulku s názvem "person".
 */
@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {

    /**
     * Jedinečný identifikátor osoby.
     * Hodnota je automaticky generována pomocí identity strategie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Jméno osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Identifikační číslo (např. IČO) osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String identificationNumber;

    /**
     * Daňové číslo (např. DIČ) osoby. Tento údaj je volitelný.
     */
    private String taxNumber;

    /**
     * Číslo účtu osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String accountNumber;

    /**
     * Kód banky osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String bankCode;

    /**
     * IBAN (International Bank Account Number) osoby. Tento údaj je volitelný.
     */
    private String iban;

    /**
     * Telefonní číslo osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String telephone;

    /**
     * Emailová adresa osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String mail;

    /**
     * Ulice a číslo domu osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String street;

    /**
     * PSČ (ZIP) osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String zip;

    /**
     * Město osoby. Tento údaj je povinný.
     */
    @Column(nullable = false)
    private String city;

    /**
     * Země osoby. Tento údaj je povinný a používá se enumerace {@link Countries}.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Countries country;

    /**
     * Volitelná poznámka k osobě.
     */
    private String note;

    /**
     * Příznak, zda je osoba skrytá. Výchozí hodnota je false.
     */
    private boolean hidden = false;

    /**
     * Seznam faktur, kde je osoba kupujícím (buyer).
     * Tento seznam je propojený přes vztah @OneToMany a mapuje se pomocí atributu "buyer"
     * ve třídě {@link InvoiceEntity}.
     */
    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> purchases;

    /**
     * Seznam faktur, kde je osoba prodávajícím (seller).
     * Tento seznam je propojený přes vztah @OneToMany a mapuje se pomocí atributu "seller"
     * ve třídě {@link InvoiceEntity}.
     */
    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sales;
}