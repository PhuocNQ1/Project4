package com.example.estateagency1.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.estateagency1.DTO.*;
import com.example.estateagency1.Mapping.*;
import com.example.estateagency1.Models.*;
import com.example.estateagency1.Service.Impl.*;
import com.example.estateagency1.Service.MailConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    public static final String UPLOADDIR_USER = "USER_IMAGES";
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
    JavaMailSender emailSender;
    @Autowired
    MailConstructor mailConstructor;
    @Autowired
    UserMapping userMapping;
    @Autowired
    PropertyMapping propertyMapping;
    @Autowired
    PropertyImageMapping propertyImageMapping;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapping blogMapping;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapping commentMapping;
    @Autowired
    PropertyFavouriteService propertyFavouriteService;
    @Autowired
    Cloudinary cloudinary;


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about(Principal principal, Model model) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);
        return "about";
    }

    @GetMapping("/property-grid")
    public String property_grid(Principal principal,
                                Model model,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                @RequestParam(value = "sorttype", required = false) String sorttype) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        String sortname = (sorttype == null) ? sorttype = "name" : sorttype;
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(sortname).ascending());
        Page<PropertyDTO> rs = null;

        if (StringUtils.isEmpty(name)) {
            rs = propertyMapping.getAllPropertyDTOPage(pageable);
        } else {
            rs = propertyMapping.getAllPropertyDTOByNameContainingPage(pageable, name);
        }

        int totalPage = rs.getTotalPages();

        if (totalPage > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPage);

            if (totalPage > 5) {
                if (end == totalPage) start = end - 5;
                else if (start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumbers);
        }
        model.addAttribute("properties", rs);

        return "property-grid";
    }

    @GetMapping("/property-single/{id}")
    public String property_single(Principal principal, Model model, @PathVariable Integer id, HttpSession session) {

        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);

        Property entity = propertyService.getPropertyById(id);
        PropertyDTO dto = new PropertyDTO();
        BeanUtils.copyProperties(entity, dto);
        model.addAttribute("property", dto);
        model.addAttribute("listImage", propertyImageMapping.propertyImageDTOList(entity));
        session.getAttribute("cart");
        return "property-single";
    }

    @GetMapping("/favourite/{id}")
    public String addFavourite(@PathVariable("id") Integer id, HttpSession session, Principal principal) {
        String loginName = (principal != null) ? principal.getName() : "";
        propertyFavouriteService.addToCart(id, session, loginName);
//        return "redirect:/test";
        return "redirect:/property-single/" + id;
    }

    //    @GetMapping("/test")
//    public String getDemo(HttpSession session,Model model,Principal principal){
////       model.addAttribute("cart",propertyFavouriteService.getCart(session));
//        model.addAttribute("cart",propertyFavouriteService.getCart(session));
//        return "test";
//    }
    @GetMapping("/property-favourite")
    public String property_favourite(Model model, HttpSession session, Principal principal) {
        String loginName = (principal != null) ? principal.getName() : "";
        model.addAttribute("cart", propertyFavouriteService.getCart(session, loginName));
        return "property-favourite";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam String subject,
                           @RequestParam String user_receiver,
                           @RequestParam String content) {
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setUser_receiver(user_receiver);
        mail.setContent(content);
        emailSender.send(mailConstructor.simpleMailMessage(mail));
        return "redirect:/property-grid";
    }

    @GetMapping("/blog-grid")
    public String blog_grid(Principal principal,
                            Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<BlogDTO> rs = blogMapping.getAllBlogDTOPage(pageable);

        int totalPage = rs.getTotalPages();

        if (totalPage > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPage);

            if (totalPage > 5) {
                if (end == totalPage) start = end - 5;
                else if (start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumbers);
        }
        model.addAttribute("blogs", rs);

        return "blog-grid";
    }

    @PostMapping("/comment")
    public String comment(@RequestParam String comment,
                          @RequestParam Integer idBlog,
                          Principal principal
    ) {
        String loginName = (principal != null) ? principal.getName() : "";
        Comment cmt = new Comment();
        cmt.setContents(comment);
        User user = userService.findUserByUserName(loginName);
        cmt.setUser(user);
        Blog blog = blogService.getBlogById(idBlog);
        cmt.setBlog(blog);

        commentService.saveComment(cmt);
        return "redirect:/blog-single/"+idBlog;
    }

    @GetMapping("/blog-single/{id}")
    public String blog_single(Principal principal, Model model, @PathVariable int id) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);
        Blog blog = blogService.getBlogById(id);
        BlogDTO dto = new BlogDTO();
        BeanUtils.copyProperties(blog, dto);
        model.addAttribute("blog", dto);
        model.addAttribute("comments", commentMapping.getAllCommentDTOByBlog(dto));
        return "blog-single";
    }

    @GetMapping("/agents-grid")
    public String agents_grid(Principal principal, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<UserDTO> rs = userMapping.getAllUserDTOPage(pageable);

        int totalPage = rs.getTotalPages();

        if (totalPage > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPage);

            if (totalPage > 5) {
                if (end == totalPage) start = end - 5;
                else if (start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumbers);
        }
        model.addAttribute("agents", rs);

        return "agents-grid";
    }

    @GetMapping("/agent-single/{id}")
    public String agents_single(Principal principal, Model model, @PathVariable int id) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);
        model.addAttribute("user", userMapping.getUserDTOByID(id));
        model.addAttribute("properties", propertyMapping.getAllPropertyDTOByUser(userMapping.getUserDTOByID(id)));
        return "agent-single";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO user, BindingResult result,
                           @RequestParam String reg_role, Model model, RedirectAttributes redirect) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRole());
            return "register";
        }
        String fileName = "https://res.cloudinary.com/dxg4uz1mx/image/upload/v1635485728/avatar_twcztm.jpg";
        if(!user.getImage().isEmpty()){
            Map rs = storageService.saveFileCloudinary(user.getImage());
            fileName = storageService.getFileNameCloudinary(rs);
        }
        User entity = new User();
        BeanUtils.copyProperties(user, entity);
        Role role = roleService.getRoleByName(reg_role);
        entity.setRoles(new HashSet<>(Arrays.asList(role)));
        entity.setAvatar(fileName);
        userService.addUser(entity);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String showHomePage(Principal principal, Model model) {
        String loginName = (principal != null) ? principal.getName() : "";
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("login_name", loginName);
        model.addAttribute("authorities", authorities);
        model.addAttribute("properties", propertyMapping.getAllPropertyDTO());
        Role rl = roleService.getRoleByName("AGENT");
        model.addAttribute("agents", userMapping.getUserDTOByRoleAgent(rl));
        return "index";
    }

}
