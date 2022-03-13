package com.example.estateagency1.Repository;

import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage,Integer> {
    List<PropertyImage> getAllByProperty(Property property);
}
