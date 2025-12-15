package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoDTO {
    private Integer idApoderado;
    private String numDocumento;
    private String nombres;
    private String apaterno;
    private String amaterno;
    private Integer genero;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDate fNacimiento;
}
