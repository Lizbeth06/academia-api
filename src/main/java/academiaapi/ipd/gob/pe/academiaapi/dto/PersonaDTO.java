package academiaapi.ipd.gob.pe.academiaapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipodocumento;
import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String genero;

//    @NotNull
    private String correo;


    private String telefono;

    private String direccion;

    private String urllinkeding;

    private LocalDate fNacimiento;

    @Size(max = 10000, message = "La descripción no puede tener más de 10000 caracteres")
    private String urlFoto;

    private TipodocumentoDTO tipodocumento;

    private UbigeoDTO ubigeo;
}
