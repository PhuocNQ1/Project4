package com.example.estateagency1.DTO;

import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO  implements Serializable {
    private Integer id;
    @JsonIgnore
    private Blog blog;
    @JsonIgnore
    private User user;
    private String contents;
    private Date createDate;
}
