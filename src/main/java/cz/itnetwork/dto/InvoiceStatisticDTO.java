package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Třída InvoiceStatisticDTO slouží jako datový přenosový objekt pro reprezentaci
 * klíčových statistických údajů faktur. Poskytuje agregované informace o fakturách,
 * včetně ročního a celkového součtu všech faktur a celkového počtu faktur v databázi.
 *
 * @Data - Generuje všechny potřebné getter/setter metody, toString, equals a hashCode metody.
 * @AllArgsConstructor - Generuje konstruktor se všemi argumenty pro tuto třídu.
 * @NoArgsConstructor - Generuje prázdný konstruktor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticDTO {
        /**
         * Celkový součet hodnot všech faktur za aktuální rok.
         * Toto pole poskytuje rychlý přehled o obratu generovaném v průběhu roku.
         */
        private Long currentYearSum;

        /**
         * Celkový součet hodnot všech faktur od začátku evidence do současnosti.
         * Toto pole slouží k poskytnutí informace o celkovém objemu obchodu firmy.
         */
        private Long allTimeSum;

        /**
         * Celkový počet faktur, které jsou uloženy v databázi.
         * Toto číslo poskytuje přehled o aktivitě a obchodních operacích prováděných firmou.
         */
        private Long invoicesCount;
}