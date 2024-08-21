package cz.itnetwork.entity.filter;

import lombok.Data;

import java.time.LocalDate;
@Data
public class InvoiceFilter {
    private Long sellerID;
    private Long buyerID;
    private String product;
    private Integer minPrice;
    private Integer maxPrice;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer limit = 10;
}
