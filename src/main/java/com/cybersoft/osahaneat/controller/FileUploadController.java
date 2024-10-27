package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.FileStorageService;
import com.cybersoft.osahaneat.service.imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
public class FileUploadController {
    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    // Endpoint xử lý yêu cầu upload file
    @PostMapping("/uploadfile")
    public ResponseEntity<ResponseData> handleFileUpload(@RequestParam("file") MultipartFile file) {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = fileStorageServiceImp.storeFile(file);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> handleDownLoadFile(@PathVariable String filename) {
        ResponseData responseData = new ResponseData();
        Resource resource = fileStorageServiceImp.loadFile(filename);
            // Return the response entity with file information and OK status
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

        }

}
