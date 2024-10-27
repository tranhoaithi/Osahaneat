package com.cybersoft.osahaneat.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageServiceImp {
    boolean storeFile(MultipartFile file);
    Resource loadFile(String fileName);

}
