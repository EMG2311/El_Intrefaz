package com.elintefaz.El_intefaz.Response;

import com.elintefaz.El_intefaz.dto.ProductOrderDto;
import com.elintefaz.El_intefaz.model.Products;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;
@Builder
@Getter
@Setter
public class OrderResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("email")
    @Email
    private String email;
    @JsonProperty("address")
    @NotBlank
    private String address;
    @JsonProperty("nCel")
    @NotBlank
    private String nCel;
    @JsonProperty("total")
    @Positive
    private Double total;
    @JsonProperty("products")
    @NotEmpty
    private List<ProductOrderDto> productos;

}