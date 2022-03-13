package com.example.estateagency1.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IStorageService {
    void saveFile(String uploadDir, String fileName,MultipartFile multipartFile) throws IOException;

    String getFileName(MultipartFile image);

    String getFileNameCloudinary(Map map);

    Map saveFileCloudinary(MultipartFile multipartFile) throws IOException;
}
