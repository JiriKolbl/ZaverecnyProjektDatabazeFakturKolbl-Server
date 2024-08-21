package cz.itnetwork.controller;



import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    /**
     * Zpracovává POST požadavky na url /invoices
     * @param invoiceDTO DTO faktury z front-endu
     * @return DTO faktury s id
     */
    @PostMapping("/invoices")
    public InvoiceDTO addInvoce(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    /**
     * Zpracovává GET požadavky z front-endu na url /invoices
     * @param invoiceFilter - parametr filtrování
     * @return List všecch faktur plnící paramatry filtru
     */
    @GetMapping("/invoices")
    public List<InvoiceDTO> getAll(InvoiceFilter invoiceFilter){
        return invoiceService.getAllInvoices(invoiceFilter);
    }

    /**
     * Zpracovává požadavky GET na jednu fakturu podle id
     * @param invoiceId hodnota id z url
     * @return jedno DTO faktury
     */
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getSingleInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    /**
     * Zpracovává požadavky GET na statistiky faktur
     * @return Jedno DTO jedné statistiky všech faktur
     */
    @GetMapping("/invoices/statistics")
    public InvoiceStatisticDTO getStatistics() {
        return invoiceService.getInvoiceStatistics();
    }

    /**
     * Zpracovává DELETE požadavky pro smazání faktury
     * @param invoiceId id z url podle kterého mažeme
     */
    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoiceById(invoiceId);
    }

    /**
     * Zpracovává PUT požadavky pro úpravu jedné dané faktury
     * @param invoiceId - Hodnota id z url
     * @param invoiceDTO - Objekt DTO z front-endu
     * @return - Upravenou fakturu
     */
    @PutMapping(("/invoices/{invoiceId}"))
    public InvoiceDTO updateInvoice(@PathVariable Long invoiceId,@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceId, invoiceDTO);
    }
}
