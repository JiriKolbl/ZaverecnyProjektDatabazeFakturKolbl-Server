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
package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByHidden(boolean hidden);
    /*
        V query COALESCE zajišťuje, že pokud bude hodnota po spojení LEFT JOINem null .. což bude u každé osoby (levá tabulka),
         která není připojena k faktuře jakožto seller(pravá tabulka invoice) (tudíž nemá žádný výdělek), tak se ve výpisu
         null nahradí za 0
     */
    /**
     * Vytváří statistiky osob a jejich obratů za určité období a ukládá je do seznamu
     * @return List PersonStatisticDTO naplněné hodnotami ke každé osobě v databázi včetně těch bez vazby k faktuře
     */
    @Query("SELECT new cz.itnetwork.dto.PersonStatisticDTO(p.id, p.name, COALESCE(SUM(i.price), 0)) " +
            "FROM person p LEFT JOIN invoice i ON p.id = i.seller AND YEAR(i.issued) = YEAR(CURRENT_DATE) - 1 " +
            "GROUP BY p.id, p.name " + "ORDER BY COALESCE(SUM(i.price), 0) DESC")
    List<PersonStatisticDTO> findPersonLastYearRevenue(); //Nepoužito

    /**
     * Vytváří statistiky osob a jejich obratů a ukládá je do seznamu
     * @return List PersonStatisticDTO naplněné hodnotami ke každé osobě v databázi včetně těch bez vazby k faktuře
     */
    @Query("SELECT new cz.itnetwork.dto.PersonStatisticDTO(p.id, p.name, COALESCE(SUM(i.price), 0)) " +
            "FROM person p LEFT JOIN invoice i ON p.id = i.seller " +
            "WHERE p.hidden = 0 " +
            "GROUP BY p.id, p.name " + "ORDER BY COALESCE(SUM(i.price), 0) DESC")
    List<PersonStatisticDTO> findPersonRevenue();
}
