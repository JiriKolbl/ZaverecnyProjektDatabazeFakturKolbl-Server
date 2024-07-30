package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticDTO {

    private long personId;

    private String personName;

    private Long revenue;

}