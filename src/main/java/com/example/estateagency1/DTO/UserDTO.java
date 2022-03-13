package com.example.estateagency1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private int id;

    @Size(min = 7,max = 20)
    @NotBlank(message = "Please input username !")
    private String username;

    @Size(min = 7,max = 20)
    @NotBlank(message = "Please input password !")
    private String password;

    @NotBlank(message = "Please input your name !")
    private String name;

    private Integer phone;
    @Email(message = "Please input correct email !")
    private String email;
    private MultipartFile image;
    private String avatar;
    private Date createDate;

}
