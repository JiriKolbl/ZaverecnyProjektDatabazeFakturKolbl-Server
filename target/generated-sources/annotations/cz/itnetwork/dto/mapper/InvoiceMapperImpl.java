package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceEntity toEntity(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setId( invoiceDTO.getId() );
        invoiceEntity.setInvoiceNumber( invoiceDTO.getInvoiceNumber() );
        if ( invoiceDTO.getIssued() != null ) {
            invoiceEntity.setIssued( Date.from( invoiceDTO.getIssued().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( invoiceDTO.getDueDate() != null ) {
            invoiceEntity.setDueDate( Date.from( invoiceDTO.getDueDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        invoiceEntity.setProduct( invoiceDTO.getProduct() );
        invoiceEntity.setPrice( invoiceDTO.getPrice() );
        invoiceEntity.setVat( invoiceDTO.getVat() );
        invoiceEntity.setNote( invoiceDTO.getNote() );
        invoiceEntity.setBuyer( personDTOToPersonEntity( invoiceDTO.getBuyer() ) );
        invoiceEntity.setSeller( personDTOToPersonEntity( invoiceDTO.getSeller() ) );

        return invoiceEntity;
    }

    @Override
    public InvoiceDTO toDto(InvoiceEntity invoiceEntity) {
        if ( invoiceEntity == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setId( invoiceEntity.getId() );
        invoiceDTO.setInvoiceNumber( invoiceEntity.getInvoiceNumber() );
        if ( invoiceEntity.getIssued() != null ) {
            invoiceDTO.setIssued( LocalDateTime.ofInstant( invoiceEntity.getIssued().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( invoiceEntity.getDueDate() != null ) {
            invoiceDTO.setDueDate( LocalDateTime.ofInstant( invoiceEntity.getDueDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        invoiceDTO.setProduct( invoiceEntity.getProduct() );
        invoiceDTO.setPrice( invoiceEntity.getPrice() );
        invoiceDTO.setVat( invoiceEntity.getVat() );
        invoiceDTO.setNote( invoiceEntity.getNote() );
        invoiceDTO.setBuyer( personEntityToPersonDTO( invoiceEntity.getBuyer() ) );
        invoiceDTO.setSeller( personEntityToPersonDTO( invoiceEntity.getSeller() ) );

        return invoiceDTO;
    }

    protected PersonEntity personDTOToPersonEntity(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( personDTO.getId() );
        personEntity.setName( personDTO.getName() );
        personEntity.setIdentificationNumber( personDTO.getIdentificationNumber() );
        personEntity.setTaxNumber( personDTO.getTaxNumber() );
        personEntity.setAccountNumber( personDTO.getAccountNumber() );
        personEntity.setBankCode( personDTO.getBankCode() );
        personEntity.setIban( personDTO.getIban() );
        personEntity.setTelephone( personDTO.getTelephone() );
        personEntity.setMail( personDTO.getMail() );
        personEntity.setStreet( personDTO.getStreet() );
        personEntity.setZip( personDTO.getZip() );
        personEntity.setCity( personDTO.getCity() );
        personEntity.setCountry( personDTO.getCountry() );
        personEntity.setNote( personDTO.getNote() );

        return personEntity;
    }

    protected PersonDTO personEntityToPersonDTO(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( personEntity.getId() );
        personDTO.setName( personEntity.getName() );
        personDTO.setIdentificationNumber( personEntity.getIdentificationNumber() );
        personDTO.setTaxNumber( personEntity.getTaxNumber() );
        personDTO.setAccountNumber( personEntity.getAccountNumber() );
        personDTO.setBankCode( personEntity.getBankCode() );
        personDTO.setIban( personEntity.getIban() );
        personDTO.setTelephone( personEntity.getTelephone() );
        personDTO.setMail( personEntity.getMail() );
        personDTO.setStreet( personEntity.getStreet() );
        personDTO.setZip( personEntity.getZip() );
        personDTO.setCity( personEntity.getCity() );
        personDTO.setCountry( personEntity.getCountry() );
        personDTO.setNote( personEntity.getNote() );

        return personDTO;
    }
}
