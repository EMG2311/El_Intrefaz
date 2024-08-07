package com.elintefaz.El_intefaz.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class CategoryDto {
    @JsonProperty("name")
    private String name;

    public CategoryDto(){}
    @JsonCreator
    public CategoryDto(@JsonProperty("name") String name) {
        this.name = name;
    }

}
