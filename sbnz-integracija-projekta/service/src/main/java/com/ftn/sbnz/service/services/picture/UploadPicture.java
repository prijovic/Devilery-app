package com.ftn.sbnz.service.services.picture;

import com.ftn.sbnz.service.converter.FileConverter;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadPicture {

    public String execute(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(fileName.substring(fileName.lastIndexOf(".")));

        File file = FileConverter.convertToFile(multipartFile, fileName);
        return this.uploadFile(file, fileName);
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("delivery-app-ac9c7.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("service/src/main/resources/delivery-app-ac9c7-firebase-adminsdk-a60oo-52a1b33686.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8);
    }
}
