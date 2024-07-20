package cz.itnetwork.controller;



import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoce(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

}
