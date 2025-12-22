package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.service.IImageService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Tag(name = "para image")
public class ImageController {
    private final IImageService imageService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "subir una imagen")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile multipartFile
    ) throws Exception {

        return ResponseEntity.ok(
                imageService.upload(multipartFile)
        );
    }


    @Operation(summary = "actualizar imagen")
    @PutMapping(value = "/updateFile/{publicId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> updateFile(
            @PathVariable String publicId,
            @RequestParam("file") MultipartFile multipartFile
    ) throws Exception {

        return ResponseEntity.ok(
                imageService.update(multipartFile, publicId)
        );
    }

}
