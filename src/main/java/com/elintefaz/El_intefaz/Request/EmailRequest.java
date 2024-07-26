package com.elintefaz.El_intefaz.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmailRequest {
    @JsonProperty("email")
    String email;
}
