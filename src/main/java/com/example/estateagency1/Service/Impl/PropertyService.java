package com.example.estateagency1.Service.Impl;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Repository.PropertyRepository;
import com.example.estateagency1.Service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements IPropertyService {
    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getAllPropertyByUser(User user) {
        return propertyRepository.getAllByUser(user);
    }

    @Override
    public Property getPropertyById(int id) {
        return propertyRepository.getById(id);
    }

    @Override
    public Optional<Property> getOptionalPropertyById(int id) {
        return propertyRepository.getPropertyById(id);
    }

    @Override
    public void updateProperty(Property property) {
        property.setCreateDate(new Date());
        propertyRepository.save(property);
    }

    @Override
    public Page<Property> getAllPropertyPage(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    @Override
    public Page<Property> getPropertyContainNamePage(Pageable pageable, String name) {
        return null;
    }

    @Override
    public Page<Property> getAllPropertyByUserPage(Pageable pageable, User user) {
        return null;
    }
}
