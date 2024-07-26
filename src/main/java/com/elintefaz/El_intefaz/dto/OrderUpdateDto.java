package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter@Builder
public class OrderUpdateDto {
    @JsonProperty("email")
    private String email;
    @JsonProperty("newEmail")
    private String newEmail;
    @JsonProperty("address")
    private String address;
    @JsonProperty("nCel")
    private String nCel;
    @JsonProperty("products")
    private List<ProductOrderDto> products;
}
