package com.example.estateagency1.DTO;

import com.example.estateagency1.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO implements Serializable {
    private Integer id;
    private Date createDate;
    @NotBlank(message = "Please input category tag !")
    private String category;
    @NotBlank(message = "Please input title !")
    private String title;
    @NotBlank(message = "Please input content !")
    private String contents;
    @JsonIgnore
    private User user;
    private String imagePath;
    private MultipartFile image;

}
