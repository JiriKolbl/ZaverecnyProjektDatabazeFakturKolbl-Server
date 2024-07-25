package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    InvoiceEntity toEntity(InvoiceDTO invoiceDTO);

    @Mapping(target = "seller.id", source = "seller.id")
    @Mapping(target = "buyer.id", source = "buyer.id")
    InvoiceDTO toDto(InvoiceEntity invoiceEntity);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    InvoiceEntity updateEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);
}
