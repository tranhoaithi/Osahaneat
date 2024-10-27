package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.service.imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService implements FileStorageServiceImp {
    @Value("${fileUpload.rootPath}")
    private String rootPath;
    private Path fileStorageLocation;
//    private Path fileStorageLocation = Paths.get(rootPath);
    // khi thể gán trực tiếp như vậy vì khi ứng dụng được start lên thì sẽ quét qua biến rootPath và lúc này biến rootPath
    // chưa có giá trị (do phải chạy qua bên file application.properties để lấy giá trị, có thể là chưa lấy được giá trị kịp thời)
// thì làm sao có thể truyền vào cho biến fileStorageLocation được.

    public FileStorageService() {
    }
    public void init(){
        fileStorageLocation = Paths.get(rootPath);
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public boolean storeFile(MultipartFile file) {
        init();
        boolean success = false;
        try {
            Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            success = true;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
        return success;
    }
    public Resource loadFile(String fileName) {
        init();
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception ex) {
            System.out.println("File not found " + ex.getMessage());
        }
        return null;
    }
}
