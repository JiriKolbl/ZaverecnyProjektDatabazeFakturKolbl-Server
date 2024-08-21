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
package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.itnetwork.constant.Countries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Třída PersonDTO definuje datovou strukturu pro přenosové účely informací o osobě.
 * Slouží jako prostředek pro sdílení dat mezi různými částmi systému nebo při komunikaci s externími API.
 *
 * @Data - Lombok anotace pro automatické generování getterů, setterů, toString, equals a hashCode metod.
 * @AllArgsConstructor - Lombok anotace pro generování konstruktoru s parametry pro všechny atributy třídy.
 * @NoArgsConstructor - Lombok anotace pro generování prázdného konstruktoru.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    /**
     * Jedinečný identifikátor osoby.
     */
    @JsonProperty("_id")
    private Long id;

    /**
     * Jméno osoby.
     */
    private String name;

    /**
     * Ičo osoby, používané pro účely identifikace v rámci systému.
     */
    private String identificationNumber;

    /**
     * Daňové identifikační číslo osoby.
     */
    private String taxNumber;

    /**
     * Číslo účtu osoby.
     */
    private String accountNumber;

    /**
     * Kód banky, ve které je účet veden.
     */
    private String bankCode;

    /**
     * Mezinárodní bankovní číslo účtu (IBAN).
     */
    private String iban;

    /**
     * Telefonní číslo osoby.
     */
    private String telephone;

    /**
     * Emailová adresa osoby.
     */
    private String mail;

    /**
     * Ulice, na které osoba bydlí nebo má sídlo.
     */
    private String street;

    /**
     * Poštovní směrovací číslo adresy osoby.
     */
    private String zip;

    /**
     * Město, ve kterém osoba bydlí nebo má sídlo.
     */
    private String city;

    /**
     * Země, ve které osoba bydlí nebo má sídlo.
     * @see Countries Enum definující podporované země.
     */
    private Countries country;

    /**
     * Volitelná poznámka o osobě.
     */
    private String note;
}

