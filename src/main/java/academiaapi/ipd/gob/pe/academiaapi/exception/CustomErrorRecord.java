package academiaapi.ipd.gob.pe.academiaapi.exception;

import java.time.LocalDateTime;

public record CustomErrorRecord(
        LocalDateTime dateTime,
        String message,
        String details
) {


}