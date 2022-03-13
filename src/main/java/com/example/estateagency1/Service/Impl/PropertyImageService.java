package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.PropertyImage;
import com.example.estateagency1.Repository.PropertyImageRepository;
import com.example.estateagency1.Service.IPropertyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyImageService implements IPropertyImageService {

    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Override
    public List<PropertyImage> getAllByProperty(Property property) {
        return propertyImageRepository.getAllByProperty(property);
    }
}
