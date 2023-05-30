package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.service.dto.response.FileNameResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileConverter {
    public static File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    public static FileNameResponse toFileNameResponse(String fileName) {
        return new FileNameResponse(fileName);
    }
}
