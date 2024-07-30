package cz.itnetwork.controller;



import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
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

    @PostMapping("/invoices")
    public InvoiceDTO addInvoce(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAll(){
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getSingleInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticDTO getStatistics() {
        return invoiceService.getInvoiceStatistics();
    }

    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoiceById(invoiceId);
    }

    @PutMapping(("/invoices/{invoiceId}"))
    public InvoiceDTO updateInvoice(@PathVariable Long invoiceId,@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceId, invoiceDTO);
    }

}
