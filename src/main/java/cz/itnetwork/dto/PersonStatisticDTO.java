package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Třída PersonStatisticDTO slouží jako datový přenosový objekt pro reprezentaci
 * statistických údajů jednotlivé osoby. Uchovává identifikační údaje a celkový
 * objem příjmů osoby.
 *
 * @Data - Generuje všechny potřebné getter/setter metody, toString, equals a hashCode metody.
 * @AllArgsConstructor - Generuje konstruktor se všemi argumenty pro tuto třídu.
 * @NoArgsConstructor - Generuje prázdný konstruktor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticDTO {
    /**
     * Jedinečný identifikátor osoby.
     */
    private Long personId;

    /**
     * Jméno osoby. Toto pole uchovává plné jméno osoby, jak je uvedeno v záznamech.
     */
    private String personName;

    /**
     * Celkový objem příjmů osoby vyjádřený v korunách.
     * Toto pole reprezentuje součet všech příjmů osoby za určité období.
     */
    private Long revenue;
}