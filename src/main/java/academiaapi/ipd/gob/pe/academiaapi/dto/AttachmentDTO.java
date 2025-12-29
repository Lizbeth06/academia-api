package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    String fileName;
    byte[] content;
}
