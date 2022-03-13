package com.example.estateagency1.Service;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IPropertyService {
    List<Property> getAllProperty();
    List<Property> getAllPropertyByUser(User user);
    Property getPropertyById(int id);
    Optional<Property> getOptionalPropertyById(int id);
    void updateProperty(Property property);
    Page<Property> getAllPropertyPage(Pageable pageable);
    Page<Property> getPropertyContainNamePage(Pageable pageable,String name);
    Page<Property> getAllPropertyByUserPage(Pageable pageable,User user);
}
