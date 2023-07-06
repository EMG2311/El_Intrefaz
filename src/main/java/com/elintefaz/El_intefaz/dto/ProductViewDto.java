package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductViewDto {
    @JsonProperty("stock")
    private Integer Stock;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("category")
    private String Category;
}
