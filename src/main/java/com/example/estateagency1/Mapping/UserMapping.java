package com.example.estateagency1.Mapping;

import com.example.estateagency1.DTO.PropertyDTO;
import com.example.estateagency1.DTO.UserDTO;
import com.example.estateagency1.Models.Role;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Service.Impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapping {
    @Autowired
    UserService userService;

   public List<UserDTO> getAllUserDTO() {
        return userService.getAllUser().stream().map(item -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

   public List<UserDTO> getUserDTOByRoleAgent(Role role) {
        return userService.findUserByRole(role).stream().map(item -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public Page<UserDTO> getAllUserDTOPage(Pageable pageable){
       return userService.getAllUserPage(pageable).map(item->{
           UserDTO dto = new UserDTO();
           BeanUtils.copyProperties(item,dto);
           return dto;
       });
    }
    public UserDTO getUserDTOByID(int id){
       User user =  userService.findUserByID(id);
       UserDTO dto = new UserDTO();
       BeanUtils.copyProperties(user,dto);
       return dto;
    }
}
