package academiaapi.ipd.gob.pe.academiaapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipodocumento;
import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Integer idPersona;

    @NotNull
    private String numDocumento;

    @NotNull
    private String nombres;

    @NotNull
    private String apaterno;

    @NotNull
    private String amaterno;

    @NotNull
    private Integer genero;

    @NotNull
    private String correo;


    private String telefono;

    private String direccion;

    private String urllinkeding;

    private LocalDate fNacimiento;

    private TipodocumentoDTO tipodocumento;

    private UbigeoDTO ubigeo;
}
