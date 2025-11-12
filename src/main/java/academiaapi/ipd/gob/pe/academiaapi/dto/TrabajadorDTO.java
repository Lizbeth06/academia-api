package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Niveleducacion;
import academiaapi.ipd.gob.pe.academiaapi.model.Oficina;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorDTO {
    private Integer idTrabajador;

    @NotNull
    private String cargo;

    @NotNull
    private String codigoTrabajador;

    @NotNull
    private LocalDate fingreso;

    @NotNull
    private LocalDate fsalida;

    @NotNull
    private String tipocontrato;

    @NotNull
    private String horariotrabajo;

    @NotNull
    private Double salario;

    private String metas;

    private String observaciones;

    private Double bonificaciones;

 

    //Relaciones
    private PersonaDTO persona;

    private NiveleducacionDTO niveleducacion;

    private OficinaDTO oficina;

}
