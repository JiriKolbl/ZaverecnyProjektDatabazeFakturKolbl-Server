package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements  InvoiceService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);

        //entita ... nastav personbuyer(repouitář.getreferencinaentitupodle id(invoiceDTO.získej buyer.jehoid))
        invoiceEntity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        invoiceEntity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));

        invoiceRepository.saveAndFlush(invoiceEntity);

        return invoiceMapper.toDto(invoiceEntity);
    }

    @Override
    public List<InvoiceDTO> getAllInvoices(){

        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDto(i))
                .collect(Collectors.toList());
    }
}
