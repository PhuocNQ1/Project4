package com.example.estateagency1.DTO;

import com.example.estateagency1.Models.Property;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeDTO implements Serializable {
    private Integer id;
    @JsonIgnore
    private Property idProperty;
    private String type;
}
