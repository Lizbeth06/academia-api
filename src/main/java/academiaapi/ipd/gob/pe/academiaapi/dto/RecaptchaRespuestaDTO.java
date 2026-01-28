package academiaapi.ipd.gob.pe.academiaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecaptchaRespuestaDTO {
    private boolean success;
    private double score;
    private String action;

    @JsonProperty("error-codes")
    private List<String> errorCodes;
}

