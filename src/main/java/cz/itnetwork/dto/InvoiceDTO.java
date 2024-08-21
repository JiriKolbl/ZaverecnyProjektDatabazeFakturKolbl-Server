package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Třída InvoiceDTO slouží jako datový přenosový objekt (DTO) pro uchování a přenos
 * informací o fakturách. Obsahuje klíčové údaje o faktuře, jako je identifikační číslo,
 * datum vystavení, datum splatnosti, produkt, cenu, DPH, poznámky a informace o kupujícím
 * a prodávajícím.
 *
 * @Data - Generuje potřebné getter/setter metody, stejně jako metody toString, equals a hashCode.
 * @AllArgsConstructor - Generuje konstruktor, který přijímá argumenty pro všechny atributy třídy.
 * @NoArgsConstructor - Generuje prázdný konstruktor bez parametrů.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    /**
     * Unikátní identifikátor faktury v databázi.
     * Tento identifikátor slouží jako primární klíč pro databázové operace.
     */
    @JsonProperty("_id")
    private Long id;

    /**
     * Číslo faktury používané pro obchodní účely.
     * Tento atribut identifikuje fakturu a je obvykle generován v rámci systému.
     */
    private int invoiceNumber;

    /**
     * Datum vystavení faktury.
     * Určuje, kdy byla faktura vytvořena. Formát data je yyyy-MM-dd.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;

    /**
     * Datum splatnosti faktury.
     * Určuje, dokdy má být faktura uhrazena. Formát data je yyyy-MM-dd.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    /**
     * Název produktu nebo služby, které jsou fakturovány.
     * Tento atribut popisuje, za jaký produkt nebo službu je faktura vystavena.
     */
    private String product;

    /**
     * Cena produktu nebo služby bez DPH.
     * Tento atribut představuje částku fakturovanou za produkt nebo službu před zdaněním.
     */
    private long price;

    /**
     * Hodnota DPH (daň z přidané hodnoty) v procentech.
     * Tento atribut specifikuje sazbu DPH aplikovanou na cenu produktu nebo služby.
     */
    private int vat;

    /**
     * Volitelná poznámka k faktuře.
     */
    private String note;

    /**
     * Tento atribut obsahuje objekt osoby nebo firmy, která obdržela fakturu.
     */
    private PersonDTO buyer;

    /**
     * Tento atribut obsahuje objekt osoby nebo firmy, která vystavila fakturu.
     */
    private PersonDTO seller;
}