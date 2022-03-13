package com.example.estateagency1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
@Data

public class PropertyFavourite implements Serializable {
    private List<PropertyFavouriteLine> propertyFavouriteLines;

    public PropertyFavourite() {
        propertyFavouriteLines = Collections.emptyList();
    }

    public PropertyFavourite(List<PropertyFavouriteLine> propertyFavouriteLines) {
        this.propertyFavouriteLines = propertyFavouriteLines;
    }
}
