package com.elintefaz.El_intefaz.dto;

import com.elintefaz.El_intefaz.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class ProductsDto {

    @JsonProperty("stock")
    private Integer Stock;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("category")
    private Integer category;

}
