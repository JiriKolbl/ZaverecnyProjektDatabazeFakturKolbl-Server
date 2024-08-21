package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;

import java.util.List;

public interface PersonService {
    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    /**
     * Vrací jednu specifickou osobu podle jejího id
     * @param id - id osoby z url podle kterého hledáme
     * @return
     */
    PersonDTO getPerson(long id);

    /**
     * Upravuje osobu tak, že oroginálnímu objektu nastaví hidden na true(1) a vytvoří novou osobu z objektu došlého z reactu
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
     * Method gets values from query in PersonRepository
     * @return PersonStatisticDTO
     */
    List<PersonStatisticDTO> getAllPersonStatistics();
}
