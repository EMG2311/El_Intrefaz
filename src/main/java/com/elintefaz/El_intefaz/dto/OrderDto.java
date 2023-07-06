package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter@Setter
public class OrderDto {
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private String address;
    @JsonProperty("nCel")
    private String nCel;
}
