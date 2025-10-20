package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Menu;
import academiaapi.ipd.gob.pe.academiaapi.model.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenurolDTO {
    private Integer idMenuRol;

    //RELACIONES
    private Menu menu;

    private Rol rol;
}
