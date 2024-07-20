package cz.itnetwork.service;

import cz.itnetwork.controller.PersonController;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements  InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    PersonController personController;



    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceRepository.saveAndFlush(invoiceEntity);

        InvoiceDTO configuratedDTO = invoiceMapper.toDto(invoiceEntity);
        configuratedDTO.setBuyer(personController.getPerson(invoiceEntity.getBuyer().getId()));
        configuratedDTO.setSeller(personController.getPerson(invoiceEntity.getSeller().getId()));

        return configuratedDTO;
    }

}
