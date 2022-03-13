package com.example.estateagency1.Controller;

import com.example.estateagency1.DTO.PropertyDTO;
import com.example.estateagency1.DTO.UserDTO;
import com.example.estateagency1.Mapping.PropertyMapping;
import com.example.estateagency1.Mapping.UserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestAPIController {
    @Autowired
    PropertyMapping propertyMapping;
    @Autowired
    UserMapping userMapping;

    @GetMapping("/listProperty")
    public ResponseEntity<List<PropertyDTO>> findAllProperty() {
        List<PropertyDTO> propertyDTOList = propertyMapping.getAllPropertyDTO();
        return ResponseEntity.ok(propertyDTOList);
    }

    @GetMapping("/listUser")
    public ResponseEntity<List<UserDTO>> findAllUser() {
        List<UserDTO> userDTOList = userMapping.getAllUserDTO();
        return ResponseEntity.ok(userDTOList);
    }
}
