package com.example.estateagency1.DTO;

import com.example.estateagency1.Models.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFavouriteLine implements Serializable {

    private PropertyDTO property;
    private int count;
    public void increaseByOne() {
        count += 1;
    }


}
