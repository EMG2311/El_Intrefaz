package com.elintefaz.El_intefaz.Response;

import com.elintefaz.El_intefaz.dto.ProductOrderDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class OrderUpdateResponse {
        @JsonProperty("IdOrder")
        private Integer id;
        @JsonProperty("email")
        private String email;
        @JsonProperty("newEmail")
        private String newEmail;
        @JsonProperty("address")
        private String address;
        @JsonProperty("nCel")
        private String nCel;
        @JsonProperty("total")
        private Double total;
        @JsonProperty("products")
        private List<ProductOrderDto> products;


}
