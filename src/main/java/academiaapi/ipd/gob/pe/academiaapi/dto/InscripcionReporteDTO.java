package academiaapi.ipd.gob.pe.academiaapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionReporteDTO {
    private String numRegistro;
    private String nombres;
    private String deporte;
    private String modalidad;
    private String etapa;
    private String complejo;
    private Integer edad;
    private String estado;
}
