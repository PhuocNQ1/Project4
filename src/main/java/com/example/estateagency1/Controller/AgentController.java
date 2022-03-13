package com.example.estateagency1.Controller;

import com.example.estateagency1.DTO.BlogDTO;
import com.example.estateagency1.DTO.PropertyDTO;
import com.example.estateagency1.DTO.PropertyImageDTO;
import com.example.estateagency1.Models.Blog;
import com.example.estateagency1.Models.Property;
import com.example.estateagency1.Models.PropertyImage;
import com.example.estateagency1.Models.User;
import com.example.estateagency1.Service.Impl.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/agent")
@PreAuthorize("hasRole('AGENT')")
public class AgentController {
    public static final String UPLOADDIR_PROPERTY = "PROPERTY_IMAGES";
    public static final String UPLOADDIR_BLOGIMAGE = "BLOG_IMAGES";
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    StorageService storageService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyImageService propertyImageService;
    @Autowired
    BlogService blogService;

    public List<PropertyDTO> propertyDTOList() {
        return propertyService.getAllProperty().stream().map(item -> {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<PropertyDTO> propertyDTOListByUser(User user) {
        return propertyService.getAllPropertyByUser(user).stream().map(item -> {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<PropertyImageDTO> getAllByProperty(Property property) {
        return propertyImageService.getAllByProperty(property).stream().map(item -> {
            PropertyImageDTO dto = new PropertyImageDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<Integer> generateNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            list.add(i);
        }
        return list;
    }

    @GetMapping("/create")
    public String createProperty(Model model, Principal principal) {
        String loginName = (principal != null) ? principal.getName() : "";
        model.addAttribute("login_name", loginName);
        model.addAttribute("property", new PropertyDTO());
        model.addAttribute("listNumber", generateNumber());
        return "agent/create-property";
    }

    @PostMapping("/create")
    public String createProperty(
            @Valid @ModelAttribute("property") PropertyDTO dto,
            BindingResult result,
            RedirectAttributes redirect,
            Model model, Principal principal) throws IOException {

        if (result.hasErrors() || dto.getImage().isEmpty()) {
            return "agent/create-property";

        }
        String loginName = (principal != null) ? principal.getName() : "";
        model.addAttribute("property", new PropertyDTO());
        model.addAttribute("login_name", loginName);

        User user = userService.findUserByUserName(loginName);

        String fileName = "";
        if (!dto.getImage().isEmpty()) {
            Map rs = storageService.saveFileCloudinary(dto.getImage());
            fileName = storageService.getFileNameCloudinary(rs);
        }

        Property entity = new Property();
        BeanUtils.copyProperties(dto, entity);
        entity.setUser(user);
        entity.setMain_image(fileName);
        user.setPropertyList(new ArrayList<>(Arrays.asList(entity)));
        userService.updateUser(user);
        redirect.addFlashAttribute("successMessage", "Saved property successfully!");

        return "redirect:/agent/create";
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("properties", propertyDTOList());
        return "agent/home";
    }

    @GetMapping("myhome")
    public String myhome(Model model, Principal principal) {
        String username = (principal != null) ? principal.getName() : "";
        User user = userService.findUserByUserName(username);
        model.addAttribute("properties1", propertyDTOListByUser(user));
        return "agent/my_property";
    }

    @GetMapping("/update/{id}")
    public String uploadImage(@PathVariable("id") Integer id, Model model) {
        PropertyDTO dto = new PropertyDTO();
        Property entity = propertyService.getPropertyById(id);
        BeanUtils.copyProperties(entity, dto);
        model.addAttribute("property", dto);
        model.addAttribute("listImage", getAllByProperty(entity));
        return "agent/mutiple_upload";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("idProperty") Integer id, Model model) {



        Property entity = propertyService.getPropertyById(id);

        //Save image on server
        List<PropertyImage> propertyImages = Arrays.asList(files).stream().map(f -> {
            PropertyImage propertyImage = new PropertyImage();
            Map rsUpload = null;
            try {
                 rsUpload = storageService.saveFileCloudinary(f);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            propertyImage.setProperty(entity);
            propertyImage.setPath(storageService.getFileNameCloudinary(rsUpload));
            return propertyImage;
        }).collect(Collectors.toList());

//        List<PropertyImage> propertyImages = Arrays.asList(files).stream().map(item -> {
//            PropertyImage propertyImage = new PropertyImage();
//            propertyImage.setPath(item.getOriginalFilename());
//            propertyImage.setProperty(entity);
//            return propertyImage;
//        }).collect(Collectors.toList());


        entity.setPropertyImageList(propertyImages);
        propertyService.updateProperty(entity);
        model.addAttribute("listImage", getAllByProperty(entity));
        return "redirect:/agent/myhome";
    }

    @GetMapping("/create-blog")
    public String create_blog(Model model) {
        model.addAttribute("blog", new BlogDTO());
        return "agent/create-blog";
    }

    @PostMapping("/create-blog")
    public String create_blog(@Valid @ModelAttribute("blog") BlogDTO dto,
                              BindingResult result,
                              Principal principal,
                              Model model,
                              RedirectAttributes redirect) throws IOException {
        if (result.hasErrors()) {
            return "agent/create-blog";
        }
        String fileName = "";
        if (!dto.getImage().isEmpty()) {
            Map rs = storageService.saveFileCloudinary(dto.getImage());
            fileName = storageService.getFileNameCloudinary(rs);
        }
        String loginName = (principal != null) ? principal.getName() : "";
        User user = userService.findUserByUserName(loginName);
        Blog blog = new Blog();
        BeanUtils.copyProperties(dto, blog);
        blog.setUser(user);
        blog.setImagePath(fileName);
        blogService.saveBlog(blog);
        redirect.addFlashAttribute("successMessage", "Saved blog successfully!");
        return "redirect:/agent/create-blog";
    }
}
