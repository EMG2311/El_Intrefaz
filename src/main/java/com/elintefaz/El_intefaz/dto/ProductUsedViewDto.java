package com.elintefaz.El_intefaz.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter@Setter
public class ProductUsedViewDto {
    private String product;
    private Integer amount;
    private Double pay;
}
