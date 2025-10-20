package academiaapi.ipd.gob.pe.academiaapi.common.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailError {
    private String errorCode;
    private String message;
}
