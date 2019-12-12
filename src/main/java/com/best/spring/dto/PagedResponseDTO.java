package com.best.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagedResponseDTO<DTOList> {
    private Long total;
    private Integer pages;
    private Integer current;
    private DTOList data;

}
