package by.home.hospital.service.impl;

import by.home.hospital.domain.User;
import by.home.hospital.dto.Avatar;
import by.home.hospital.service.StorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class ImageStoreService implements StorageService {

    @Autowired
    UserService userService;



    @Override
    public void store(Integer id, MultipartFile file) throws IOException {
        String string = UUID.randomUUID().toString();
        User user = this.userService.getUserById(id);
        File file2 = new File(string);
        user.setAvatarFileName(file2.getAbsolutePath());
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(file2)) {
            IOUtils.copy(in, out);
        }
       this.userService.save(user);
    }

    @Override
    public Avatar getFile(Integer id) {
        User user = this.userService.getUserById(id);
        if (user.getAvatarFileName() != null) {
            Avatar avatar = new Avatar();
            avatar.setFullFilePath(user.getAvatarFileName());
            return avatar;
        }
        return null;
    }
}