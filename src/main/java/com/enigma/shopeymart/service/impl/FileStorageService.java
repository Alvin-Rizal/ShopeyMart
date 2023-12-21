package com.enigma.shopeymart.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    //Taruh file atau  link tempat kita akses file
    private final Path fileStorageLocation = Paths.get("/home/user/IdeaProjects/shopeymart/src/main/java/com/enigma/shopeymart/file");

    //Method Untuk save file
    public FileStorageService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e) {
            throw new RuntimeException("Could not Create the Directories where the uploaded file set to storage");
        }
    }

    //Logic untuk melakukan penyimpanan file
    public String storageFile(MultipartFile file) {

        //lakukan pengecekan tipe file
        String mimeType = file.getContentType();

        //Check file type to just image

        if (mimeType == null || (!mimeType.startsWith("image/"))){
            throw new RuntimeException("Invalid Upload File , Mot Match with Requirement");
        }

        try {
            String filename = file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return file.getOriginalFilename();
        }catch (IOException e) {
            throw new RuntimeException("Could not Store " + file.getOriginalFilename() +" please check again " + e);
        }
    }

    public Resource downloadFile(String nameFile) throws FileNotFoundException {
        //temukan file path
        try {
            Path targetLocation = this.fileStorageLocation.resolve(nameFile).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());
            if (resource.exists()){
                return resource;
            }else throw new RuntimeException("File not Found " + nameFile);
        }catch (MalformedURLException e) {
            throw new FileNotFoundException("File Not Found " + nameFile);
        }
    }
}
