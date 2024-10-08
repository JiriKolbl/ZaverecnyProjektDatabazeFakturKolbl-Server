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
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * Zpracovává požadavek POST na url /persons pro vytvoření nové osoby
     * @param personDTO - objekt DTO z front-endu
     * @return uložený objekt DTO z databáze s id
     */
    @PostMapping("/persons")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return  personService.addPerson(personDTO);
    }

    /**
     * Zpracovává požadavek GET na url /persons
     * @return List všech osob z databéze PersonDTO
     */
    @GetMapping("/persons")
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    /**
     * Zpracovává požadavek Delete (skryje osobu, nemaže ji)
     * @param personId parametr id z url
     */
    @DeleteMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    /**
     * Zpracovává požadavek GET a pomocí id nalezne v databázi jednu osobu
     * @param personId paramtr id z url
     * @return jedno PersonDTO z databáze
     */
    @GetMapping("/persons/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    /**
     * Zpracovává požadavek PUT a upravuje záznam osobu z databáze
     * @param personId pramatetr id z url
     * @param personDTO objekt z formuláře front-endu
     * @return Změněný záznam PersonDTO
     */
    @PutMapping("/persons/{personId}")
    public PersonDTO updatePerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
        return personService.updatePerson(personId, personDTO);
    }

    /**
     * Zpracovává požadavek GET a vyhledá přes repozitář všechny faktury kde osoba s daným identificationNumber
     * figuruje jako seller
     * @param identificationNumber parametr identificationNumber z url
     * @return List všech faktur kde osoba s daným identificationNumber figuruje jako seller
     */
    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getAllSellerInvoices(@PathVariable String identificationNumber) {
        return personService.getInvoicesBySeller(identificationNumber);
    }

    /**
     * Zpracovává požadavek GET a vyhledá přes repozitář všechny faktury kde osoba s daným identificationNumber
     * figuruje jako buyer
     * @param identificationNumber parametr identificationNumber z url
     * @return List všech faktur kde osoba s daným identificationNumber figuruje jako buyer
     */
    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getAllBuyerInvoices(@PathVariable String identificationNumber) {
        return personService.getInvoicesByBuyer(identificationNumber);
    }

    /**
     * Zpracovává požadavek GET a přes QUERY v databázi spočítá statistiku všech osob v databázi
     * @return List statistik všech osob
     */
    @GetMapping("/persons/statistics")
    public List<PersonStatisticDTO> getAllPersonStatistic() {
        return personService.getAllPersonStatistics();
    }
}



