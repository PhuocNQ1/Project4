package com.example.estateagency1.Mapping;

import com.example.estateagency1.DTO.PropertyDTO;
import com.example.estateagency1.DTO.UserDTO;
import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Service.Impl.PropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PropertyMapping {
    @Autowired
    PropertyService propertyService;

   public List<PropertyDTO> getAllPropertyDTO() {
        return propertyService.getAllProperty().stream().map(item -> {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

   public Page<PropertyDTO> getAllPropertyDTOPage(Pageable pageable) {
        return propertyService.getAllPropertyPage(pageable).map(item -> {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        });
    }

   public Page<PropertyDTO> getAllPropertyDTOByNameContainingPage(Pageable pageable, String name) {
        return propertyService.getPropertyContainNamePage(pageable, name).map(item -> {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        });
    }
    public List<PropertyDTO> getAllPropertyDTOByUser(UserDTO user){
       User entity = new User();
       BeanUtils.copyProperties(user,entity);
       return propertyService.getAllPropertyByUser(entity).stream().map(item->{
           PropertyDTO dto = new PropertyDTO();
           BeanUtils.copyProperties(item,dto);
           return dto;
       }).collect(Collectors.toList());
    }
    public PropertyDTO getPropertyDTOByID(Integer id){
       Property property = propertyService.getPropertyById(id);
       PropertyDTO dto = new PropertyDTO();
       BeanUtils.copyProperties(property,dto);
       return dto;
    }


}
