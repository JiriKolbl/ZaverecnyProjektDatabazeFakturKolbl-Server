package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;

import java.util.List;

public interface PersonService {
    /**
     * Vytvoří novou osobu a uloží do databáze
     * @param personDTO objekt z front-endu
     * @return PersonDTO s přiřazeným id
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * Nastavuje atribut osoby hidden na true a ukládá
     * @param id osoby ke smazání
     */
    void removePerson(long id);

    /**
     * Získá všechny osoby s hidden na false
     * @return List všech osob s hidden nastaveným na false
     */
    List<PersonDTO> getAll();

    /**
     * Vrací jednu specifickou osobu podle jejího id
     * @param id - id osoby z url podle kterého hledáme
     * @return
     */
    PersonDTO getPerson(long id);

    /**
     * Upravuje osobu tak, že oroginálnímu objektu nastaví hidden na true(1) a vytvoří novou osobu z objektu
     * došlého z reactu
     * @param id - id osoby kterou chceme upravit
     * @param personDTO objekt nové(upravené) osoby došlé z frontendu
     * @return vrací již upravenou osobu
     */
    PersonDTO updatePerson(long id, PersonDTO personDTO);

    /**
     * Metoda hledá faktury, které se váží k osobě jakožto seller podle ičo
     * @param identificationNumber ičo osoby podle kterého hledáme fakturu
     * @return vrací seznam faktur, které filtrujeme v repozitáři podle toho zda osoba ke které se váže je v ní seller
     */
    List<InvoiceDTO> getInvoicesBySeller(String identificationNumber);
    /**
     * Metoda hledá faktury, které se váží k osobě jakožto buyer podle ičo
     * @param identificationNumber ičo osoby podle kterého hledáme fakturu
     * @return vrací seznam faktur, které filtrujeme v repozitáři podle toho zda osoba ke které se váže je v ní buyer
     */
    List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber);

    /**
     * Metoda vypočítá přes databázový příkaz statistiku jedné osoby a takto vytvoří list statistik všech osob
     * s hidden nastaveným na false
     * @return PersonStatisticDTO
     */
    List<PersonStatisticDTO> getAllPersonStatistics();
}
