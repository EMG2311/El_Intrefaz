package com.elintefaz.El_intefaz.dto;

import com.elintefaz.El_intefaz.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;
@Getter@Setter@Builder
public class ProductsDto {
    @JsonProperty("name")
    @NotBlank
    private String name;
    @JsonProperty("stock")
    @Positive
    private Integer stock;
    @JsonProperty("price")
    @Positive
    private Double price;
    @JsonProperty("category")
    private String category;

}
