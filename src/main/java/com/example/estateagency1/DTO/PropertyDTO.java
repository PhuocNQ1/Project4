package com.example.estateagency1.DTO;

import com.example.estateagency1.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class PropertyDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "Please input your property name !")
    private String name;

    @NotBlank(message = "Please input your property location !")
    private String location;

    private String status;

    private Double area;

    private Integer beds;

    private Integer baths;

    private String garage;

    private String main_image;

    private MultipartFile image;

    private BigDecimal amount;

    private Date createDate;

    private String description;

    @JsonIgnore
    private User user;

    private MultipartFile[] listImage;

}
