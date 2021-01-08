package by.home.hospital.service;

import by.home.hospital.dto.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface StorageService {

    void store(Integer id, MultipartFile file) throws IOException;

    Avatar getFile(Integer id);
}