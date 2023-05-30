package com.ftn.sbnz.service.services.picture;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DownloadPicture {

    public String execute(String fileName) throws IOException {
        String destFilePath = new File("data/images/" + fileName).getAbsolutePath();

        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("service/src/main/resources/delivery-app-ac9c7-firebase-adminsdk-a60oo-52a1b33686.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        storage.downloadTo(BlobId.of("delivery-app-ac9c7.appspot.com", fileName), Paths.get(destFilePath));
        return destFilePath;
    }
}
