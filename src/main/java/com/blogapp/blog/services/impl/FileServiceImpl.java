package com.blogapp.blog.services.impl;

import com.blogapp.blog.exceptions.IncorrectFileFormatException;
import com.blogapp.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // file Name eg -> abc.png
        String name = file.getOriginalFilename();

        //validate the image
        String fileExtension = name.substring(name.lastIndexOf('.'));
        if(!(fileExtension.equals(".png") || fileExtension.equals(".jpg") || fileExtension.equals(".jpeg"))) {
            throw new IncorrectFileFormatException(fileExtension);
        }

        // random name generate file
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        // full path
        String filePath = path + File.separator + fileName;

        // create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // file copy
        Files.copy(file.getInputStream() , Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        // db logic to return inputStream
        return inputStream;
    }
}
