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

        return invoiceEntity;
    }

    @Override
    public InvoiceDTO toDto(InvoiceEntity invoiceEntity) {
        if ( invoiceEntity == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setSeller( personEntityToPersonDTO( invoiceEntity.getSeller() ) );
        invoiceDTO.setBuyer( personEntityToPersonDTO1( invoiceEntity.getBuyer() ) );
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

        return invoiceDTO;
    }

    @Override
    public InvoiceEntity updateEntity(InvoiceDTO source, InvoiceEntity target) {
        if ( source == null ) {
            return target;
        }

        target.setId( source.getId() );
        target.setInvoiceNumber( source.getInvoiceNumber() );
        if ( source.getIssued() != null ) {
            target.setIssued( Date.from( source.getIssued().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        else {
            target.setIssued( null );
        }
        if ( source.getDueDate() != null ) {
            target.setDueDate( Date.from( source.getDueDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        else {
            target.setDueDate( null );
        }
        target.setProduct( source.getProduct() );
        target.setPrice( source.getPrice() );
        target.setVat( source.getVat() );
        target.setNote( source.getNote() );

        return target;
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

    protected PersonDTO personEntityToPersonDTO1(PersonEntity personEntity) {
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
