package com.example.estateagency1.Mapping;

import com.example.estateagency1.DTO.BlogDTO;
import com.example.estateagency1.Service.Impl.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogMapping {
    @Autowired
    BlogService blogService;

   public Page<BlogDTO> getAllBlogDTOPage(Pageable pageable){
        return blogService.getAllBlogPage(pageable).map(item->{
            BlogDTO dto = new BlogDTO();
            BeanUtils.copyProperties(item,dto);
            return dto;
        });
    }
    public List<BlogDTO> getAllBlogDTO(){
        return blogService.getAllBlog().stream().map(item->{
            BlogDTO dto = new BlogDTO();
            BeanUtils.copyProperties(item,dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
