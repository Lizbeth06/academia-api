package academiaapi.ipd.gob.pe.academiaapi.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dfwuefyqt",
                "api_key", "483971483385743",
                "api_secret", "2CzGLQMP5zFtFYPEi29prlWyGsU"
        ));
    }
}
