package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    /**
     * Metoda vytvoří objekt osoby a uloží do databáze
     * @param invoiceDTO - objekt osoby z front-endu
     * @return InvoiceDTO s přiřazeným id
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Metoda získá veškeré faktury z databáze splňující filtr
     * @param invoiceFilter objekt filtru podle kterého filtrujeme
     * @return vyfiltrovaný list DTO faktur
     */
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

    /**
     * Metoda získá jeden objekt faktury podle id z url
     * @param invoiceId id parametr z url
     * @return Jeden objekt DTO faktury
     */
    InvoiceDTO getInvoiceById(long invoiceId);

    /**
     * Smaže fakturu z databáze
     * @param invoiceId param z url podle kterého mažeme
     */
    void deleteInvoiceById(long invoiceId);

    /**
     * Upraví existující záznam faktury nalezené podle id z url
     * @param invoiceId param id z url
     * @param invoiceDTO objekt z front-end formuláře
     * @return upravený objekt uložený do databáze
     */
    InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * Metoda pomocí query z databáze spočítá statistiku všech faktur
     * @return jediný obejtk InvoiceStatisticDTO
     */
    InvoiceStatisticDTO getInvoiceStatistics();
}
