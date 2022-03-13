package com.example.estateagency1.Mapping;

import com.example.estateagency1.DTO.PropertyImageDTO;
import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Service.Impl.PropertyImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyImageMapping {

    @Autowired
    PropertyImageService propertyImageService;

   public List<PropertyImageDTO> propertyImageDTOList(Property property) {
        return propertyImageService.getAllByProperty(property).stream().map(item -> {
            PropertyImageDTO dto = new PropertyImageDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
