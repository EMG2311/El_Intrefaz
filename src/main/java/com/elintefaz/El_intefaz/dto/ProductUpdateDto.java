package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ProductUpdateDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("newName")
    private String newName;
    @JsonProperty("stock")
    private Integer stock;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("category")
    private String category;
}
