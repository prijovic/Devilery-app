package com.ftn.sbnz.service.services.picture;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DeletePicture {

    public void execute(String fileName) throws IOException {
        BlobId blobId = BlobId.of("delivery-app-ac9c7.appspot.com", fileName);
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("service/src/main/resources/delivery-app-ac9c7-firebase-adminsdk-a60oo-52a1b33686.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(blobId);
        if (blob != null) {
            blob.delete();
        }
    }
}
