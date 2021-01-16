package com.cooksy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KrogerResultDto {

    private Integer start;
    private Integer limit;
    private Integer total;
    private List<KrogerProductDto> products;
}
