package academiaapi.ipd.gob.pe.academiaapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IImageService {

    Map<String, String> upload(MultipartFile multipartFile, String folder) throws Exception;

    Map<String, String> update(MultipartFile multipartFile, String publicId) throws Exception;
}

