package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

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

    @Override
    public InvoiceDTO getInvoiceById(long invoiceId){
        return invoiceMapper.toDto(fetchInvoiceById(invoiceId));
    }

    @Override
    public ResponseEntity<Void> deleteInvoiceById(long invoiceId) {
        InvoiceEntity entity = fetchInvoiceById(invoiceId);
        invoiceRepository.delete(entity);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @Transactional
    public InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO) {

        InvoiceEntity entity = fetchInvoiceById(invoiceId);

        invoiceDTO.setId(invoiceId);

        invoiceMapper.updateEntity(invoiceDTO, entity);

        entity.setBuyer(personRepository.getReferenceById(invoiceDTO.getBuyer().getId()));
        entity.setSeller(personRepository.getReferenceById(invoiceDTO.getSeller().getId()));

        InvoiceEntity saved = invoiceRepository.save(entity);


        return invoiceMapper.toDto(saved);
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
}
