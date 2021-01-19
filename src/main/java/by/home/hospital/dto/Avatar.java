package by.home.hospital.dto;

import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public class Avatar {

    private String fileName;

    private String fullFilePath;

    public InputStream getData() throws IOException {
        return new FileInputStream(new File(fullFilePath));
    }

    public String getFileName() {
        return fileName;
    }
}