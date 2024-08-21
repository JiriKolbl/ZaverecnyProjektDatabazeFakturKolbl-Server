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
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    // Fields region
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    //Public methods region
    /**
     * Metoda uloží nový záznam do tabulku z údajů z PersonDTO bez id
     * @param personDTO osoba k vytvoření
     * @return vrací zpět PersonDTO osoby kterou uložila již s id
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);

        return personMapper.toDTO(entity);
    }

    /**
     * Metoda Metoda která nic nevrací. Osobu určenou pro smazání pouze skryje pomocí nastavení isHidden na true(1)
     * a uloží
     * @param personId id osoby pro smazání
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);
            personRepository.save(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    /**
     * Metoda získávající veškeré osoby z tabulky persons
     * @return List všech osob v dat. typu PersonDTO
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .toList();
                //.collect(Collectors.toList());
    }

    /**
     *
     * @param id dat. typ long
     * @return jediné dto osoby PersonDTO nalezené podle specifického id
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public PersonDTO getPerson(long id) {
        return personMapper.toDTO(fetchPersonById(id));
    }

    /**
     *  Metoda pro úpravu osoby v dazabázi podle jejího id ... Původní osobu nastaví hidden na true(1), čímž ji schová.
     *  Jako parametry bere id podle kterého hledá v databázi a původní osobu z databáze kterou vkládá do formuláře
     * @param personId
     * @param personDTO
     * @return nové dto osoby PersonDTO (nově vytvořené) s pozměněnými údaji původní osoby
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public PersonDTO updatePerson(long personId, PersonDTO personDTO) {
        PersonEntity person = fetchPersonById(personId);
        person.setHidden(true);
        personRepository.save(person);

        return addPerson (personDTO);
    }

    /**
     * Metoda filtruje faktury podle iča kde osoba figuruje jako seller na úrovni databáze a urychluje tak proces
     * filtrování při vysokém počtu záznamů v databázi
     * @param identificationNumber
     * @return List faktur kde ičo osoby seller je rovno identificationNumber
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public List<InvoiceDTO> getInvoicesBySeller(String identificationNumber) {
        return invoiceRepository.findBySeller_IdentificationNumber(identificationNumber)
                .stream()
                .map(i -> invoiceMapper.toDto(i))
                .collect(Collectors.toList());
        /*
        Původní metoda filtrující faktury na úrovni Springu
        List<InvoiceEntity> entities = invoiceRepository.findAll();
        List<InvoiceDTO> result = new ArrayList<>();
        for (InvoiceEntity e : entities) {
            if(e.getSeller().getIdentificationNumber().equals(identificationNumber)) {
                result.add(invoiceMapper.toDto(e));
            }
        }
        return result;
         */
    }

    /**
     * Metoda filtruje faktury podle iča kde osoba figuruje jako buyer na úrovni databáze
     * @param identificationNumber
     * @return List faktur kde ičo osoby buyer je rovno identificationNumber
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber) {
        return invoiceRepository.findByBuyer_IdentificationNumber(identificationNumber)
                .stream()
                .map(i -> invoiceMapper.toDto(i))
                .collect(Collectors.toList());

        /**
         * Moje původní podoba metody která filtrovala na úrovni Springu
        return invoiceRepository.findAll()
                .stream()
                .filter(i -> i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                .map(i -> invoiceMapper.toDto(i))
                .collect(Collectors.toList());
        */
    }

    /**
     * Metoda vytvářející statistiky podle šablony PersonStatisticDTO a počítající její hodnoty na úrovni
     * databázovehé query nacházejícím se v InvoiceRepository
     * @return List DTO statistiky pro veškeré osoby v tabulce
     * @see cz.itnetwork.controller.PersonController <- využité v
     */
    @Override
    public List<PersonStatisticDTO> getAllPersonStatistics() {
        return personRepository.findPersonRevenue();
    }

    // region: Private methods
    /**
     * Metoda fetchuje osobu na základě id předaného v long z person repozitáře a ukládá ji do entity
     * @param id parametr podle kterého hledá
     * @return získaná osoba
     * @throws org.webjars.NotFoundException * Pokud ji nenajde, vyhodí error, že v databázi nenalezla osobu s tímto id
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
    // endregion
}
