package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.services.picture.GetPicture;
import com.ftn.sbnz.service.services.picture.UploadPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/picture")
@RequiredArgsConstructor
public class PictureController {
    private final UploadPicture uploadPicture;
    private final GetPicture getPicture;

    @PostMapping
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadPicture.execute(file);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public InputStreamResource download(@RequestParam String fileName) throws IOException {
        return getPicture.execute(fileName);
    }
}
