package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.converter.FileConverter;
import com.ftn.sbnz.service.dto.response.FileNameResponse;
import com.ftn.sbnz.service.services.picture.DeletePicture;
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
    private final DeletePicture deletePicture;

    @PostMapping
    @ResponseBody
    public FileNameResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        return FileConverter.toFileNameResponse(uploadPicture.execute(file));
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public InputStreamResource download(@RequestParam String fileName) throws IOException {
        return getPicture.execute(fileName);
    }

    @DeleteMapping
    public void delete(@RequestParam String fileName) throws IOException {
        deletePicture.execute(fileName);
    }
}
