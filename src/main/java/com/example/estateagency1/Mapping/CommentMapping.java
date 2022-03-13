package com.example.estateagency1.Mapping;

import com.example.estateagency1.DTO.BlogDTO;
import com.example.estateagency1.DTO.CommentDTO;
import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Service.Impl.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapping {
    @Autowired
    CommentService commentService;

   public List<CommentDTO> getAllCommentDTOByBlog(BlogDTO blog){
       Blog bg = new Blog();
       BeanUtils.copyProperties(blog,bg);
        return commentService.getAllCommentByBlog(bg).stream().map(item->{
            CommentDTO dto = new CommentDTO();
            BeanUtils.copyProperties(item,dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
