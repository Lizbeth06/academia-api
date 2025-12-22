package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.service.IImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map<String, String> upload(MultipartFile multipartFile) throws Exception {

        File f = convertToFile(multipartFile);

        Map<String, Object> response = cloudinary.uploader().upload(
                f,
                ObjectUtils.asMap("resource_type", "auto")
        );

        JSONObject json = new JSONObject(response);

        f.delete();

        Map<String, String> result = new HashMap<>();
        result.put("url", json.getString("secure_url"));
        result.put("public_id", json.getString("public_id"));

        return result;
    }

    @Override
    public Map<String, String> update(MultipartFile multipartFile, String publicId) throws Exception {

        File f = convertToFile(multipartFile);

        Map<String, Object> response = cloudinary.uploader().upload(
                f,
                ObjectUtils.asMap(
                        "public_id", publicId,
                        "overwrite", true,
                        "invalidate", true,
                        "resource_type", "auto"
                )
        );

        JSONObject json = new JSONObject(response);

        f.delete();

        Map<String, String> result = new HashMap<>();
        result.put("url", json.getString("secure_url"));
        result.put("public_id", json.getString("public_id"));

        return result;
    }

    private File convertToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }

}
