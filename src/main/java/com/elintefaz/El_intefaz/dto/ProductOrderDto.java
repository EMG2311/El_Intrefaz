package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter@Setter@Builder
public class ProductOrderDto {
    @NotBlank
    @JsonProperty("name")
    private String name;
    @Positive
    @JsonProperty("quantity")
    private Integer quantity;
}
