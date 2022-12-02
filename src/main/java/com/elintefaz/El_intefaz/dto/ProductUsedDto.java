package com.elintefaz.El_intefaz.dto;


import com.elintefaz.El_intefaz.model.Order;
import com.elintefaz.El_intefaz.model.Products;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductUsedDto {
    @JsonProperty("Idproduct")
    private Integer Idproduct;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("IdOrder")
    private Integer IdOrder;
}
