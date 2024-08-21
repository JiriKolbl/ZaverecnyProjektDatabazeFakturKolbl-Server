package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    /**
     * Method (create) for adding of invoice to database
     * @param invoiceDTO - objekt of invoice from frontend
     * @return object of invoice returned from database with id
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Method (read) for getting all invoices
     * @param invoiceFilter object of filter from frontend (empty or with some/all attributes selected)
     * @return filtered or unfiltered list of invoice objects
     */
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

    /**
     * Method (read) gets single invoice selected by its id
     * @param invoiceId id from url
     * @return single object of invoice by id
     */
    InvoiceDTO getInvoiceById(long invoiceId);

    /**
     * Deletes single invoice from invoice table in database
     * @param invoiceId param from url
     */
    void deleteInvoiceById(long invoiceId);

    /**
     * Updates extising invoice from databse table invoice
     * @param invoiceId param od from url
     * @param invoiceDTO object from frontend
     * @return updated object saved to database
     */
    InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * Method calls Query from InvoiceRepository
     * @return object of InvoiceStatisticDTO
     */
    InvoiceStatisticDTO getInvoiceStatistics();
}
