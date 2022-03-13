package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.PropertyImage;

import java.util.List;

public interface IPropertyImageService {
    List<PropertyImage> getAllByProperty(Property property);
}
