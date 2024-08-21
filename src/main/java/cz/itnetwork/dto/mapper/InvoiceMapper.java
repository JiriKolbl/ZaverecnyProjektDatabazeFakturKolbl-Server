package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    /**
     * Převod z DTO do entity
     * @param invoiceDTO DTO ze kterého převádíve
     * @return cílovou entitu
     */
    InvoiceEntity toEntity(InvoiceDTO invoiceDTO);

    /**
     * Převod z Entity do DTO
     * @param invoiceEntity Entita kterou převádíme
     * @return cílové DTO
     */
    InvoiceDTO toDto(InvoiceEntity invoiceEntity);

    /**
     * Metoda slouží pro úpravu existující entity, při úpravě ignoruje atributy seller a buyer
     * @param source DTO z formuláře front-endu
     * @param target entita kterou chceme upravit
     * @return upravenou entitu
     */
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    InvoiceEntity updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);
}
