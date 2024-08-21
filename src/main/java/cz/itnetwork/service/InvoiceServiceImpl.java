package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements  InvoiceService{
    // region Fields
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    // endregion

    @Autowired
    private InvoiceMapper invoiceMapper;

    // region Public methods
    /**
     * Ukládá objekt InvoiceDTO předaný z view bez id do tabulky invoice
     * @param invoiceDTO
     * @return objekt InvoiceDTO který uložila do databéze s přiřazeným id
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceEntity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        invoiceEntity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        invoiceRepository.save(invoiceEntity);

        return invoiceMapper.toDto(invoiceEntity);
    }

    /**
     * Metoda vrací veškeré faktury nalezené v tabulce invoice nebo veškere faktury nalezené v tabulce invoice splňující
     * parametry filtru
     * @param invoiceFilter objekt filtru zadaného uživatelem podle kterého hledáme v Invoice Specification
     * @see cz.itnetwork.entity.repository.specification.InvoiceSpecification
     * @see cz.itnetwork.entity.filter.InvoiceFilter
     * @return List InvoiceDTO všech faktur z tabulky nebo všech faktur z tabulky splňující požadované paramatry filtru
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter){
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(i -> invoiceMapper.toDto(i))
                .collect(Collectors.toList());
    }

    /**
     * Metoda vyhledá a vrátí fakturu z tabulky podle zadaného id
     * @param invoiceId id faktury podle kterého hledáme
     * @return objekt faktury nalezené podle id z param
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    public InvoiceDTO getInvoiceById(long invoiceId){
        return invoiceMapper.toDto(fetchInvoiceById(invoiceId));
    }

    /**
     * Smaže fakturu z databáze podle zadaného id
     * @param invoiceId parametr pod kterého hledáme fakturu pro smazání
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    public void deleteInvoiceById(long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    /**
     * Metoda získá objekt faktury z databáze, pak ho upraví, setne Id, ručně setne objekty Person(buyer a seller) protože
     * je mapper ignoruje a uloží do databáze pod stejným id jako původní objekt
     * @param invoiceId id faktury podle kterého hledáme
     * @param invoiceDTO objekt faktury který upravujeme
     * @return vrací upravený objekt faktury
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    @Transactional // TODO vzpomen si
    public InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = fetchInvoiceById(invoiceId);
        invoiceDTO.setId(invoiceId);
        invoiceMapper.updateEntity(invoiceDTO, entity);
        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));
        InvoiceEntity saved = invoiceRepository.save(entity);

        return invoiceMapper.toDto(saved);
    }

    /**
     * Získává údaje z query z invoiceRepozitory a ukládá do DTO
     * @return InvoiceStatisticDTO objekt se spočítanými statistikami na úrovni databáze
     * @see cz.itnetwork.controller.InvoiceController <- využito v
     */
    @Override
    public InvoiceStatisticDTO getInvoiceStatistics() {

        return invoiceRepository.findInvoiceStatistic();
        /*
        Double currentYearSum = invoiceRepository.findSumInvoicesPriceOfThisYear();
        Double allTimeSum = invoiceRepository.findSumOfAllInvoicesPrice();
        Long invoicesCount = invoiceRepository.findCountOfAllInvoices();

        return  new InvoiceStatisticDTO(currentYearSum, allTimeSum, invoicesCount);
         */
    }

    // region Private methods
    /**
     * Pomocná metoda získává entitu faktury podle id zadaného v param
     * @param id id faktury podle kterého hledáme
     * @return InvoiceEntity
     * @OrElseThrow - vyhodí vyjímku že faktura pod zadaným id nebyla nalezena
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
    // endregion
}
