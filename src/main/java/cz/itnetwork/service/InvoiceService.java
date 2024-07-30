package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoices();

    InvoiceDTO getInvoiceById(long invoiceId);

    void deleteInvoiceById(long invoiceId);

    InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    InvoiceStatisticDTO getInvoiceStatistics();
}
