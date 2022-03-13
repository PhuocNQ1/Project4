package com.example.estateagency1.Config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
@Configuration
public class CloudinaryBean {
    @Bean
   public Cloudinary cloudinary(){
        Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap(
                "cloud_name","dxg4uz1mx",
                "api_key","817639732158947",
                "api_secret","42urzSwXo3PQRkvxJutXn6G3Z9A",
                "secure",true
        ));
        return cloudinary;
    }
}
