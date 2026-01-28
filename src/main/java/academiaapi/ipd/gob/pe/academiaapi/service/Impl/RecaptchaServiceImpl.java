package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.RecaptchaRespuestaDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
@RequiredArgsConstructor
public class RecaptchaServiceImpl {

    @Value("${recaptcha.secret}")
    private String secretKey;

    @Value("${recaptcha.threshold:0.5}")
    private double threshold;

    private static final String GOOGLE_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validarToken(String token, String actionEsperada) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("secret", secretKey);
        body.add("response", token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<RecaptchaRespuestaDTO> response =
                restTemplate.postForEntity(
                        GOOGLE_URL,
                        request,
                        RecaptchaRespuestaDTO.class
                );

        if (response.getBody() == null) {
            return false;
        }

        RecaptchaRespuestaDTO r = response.getBody();

        return r.isSuccess()
                && r.getScore() >= threshold
                && actionEsperada.equals(r.getAction());
    }
}
