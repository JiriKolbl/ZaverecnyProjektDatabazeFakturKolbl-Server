package cz.itnetwork.entity.repository;


import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {
    /**
     * Metoda pomocí query získá statistiky faktur
     * @return InvoiceStatisticDTO naplněné hodnotami
     */
    @Query("""
            SELECT new cz.itnetwork.dto.InvoiceStatisticDTO(
                SUM(CASE WHEN YEAR(i.issued) = YEAR(CURRENT_DATE) THEN i.price ELSE 0 END),
                SUM(i.price),
                COUNT(*))
            FROM invoice i
            """)
    InvoiceStatisticDTO findInvoiceStatistic();

    /**
     * Metoda hledá faktury podle oči kde osoba figuruje jako seller
     * @param identificationNumber ičo získané z url
     * @return List entit faktur kde osoba s daným ičem figuruje jako seller
     */
    List<InvoiceEntity> findBySeller_IdentificationNumber(String identificationNumber);
    /**
     * Metoda hledá faktury podle oči kde osoba figuruje jako buyer
     * @param identificationNumber ičo získané z url
     * @return List entit faktur kde osoba s daným ičem figuruje jako buyer
     */
    List<InvoiceEntity> findByBuyer_IdentificationNumber(String identificationNumber);
}
