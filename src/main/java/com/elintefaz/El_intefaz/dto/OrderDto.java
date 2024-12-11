package com.elintefaz.El_intefaz.dto;

import com.elintefaz.El_intefaz.model.Products;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter@Setter@Builder
public class OrderDto {
    @JsonProperty("email")
    @Email
    private String email;
    @JsonProperty("address")
    private String address;
    @JsonProperty("nCel")
    private String nCel;
    @Getter
    @JsonProperty("products")
    private List<ProductOrderDto> products;

}
