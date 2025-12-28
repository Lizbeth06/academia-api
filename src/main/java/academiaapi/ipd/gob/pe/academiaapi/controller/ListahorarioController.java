package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ConvocatoriaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorariobloqueDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.service.IConvocatoriaService;
import academiaapi.ipd.gob.pe.academiaapi.service.IListahorarioService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/listahorario")
@RequiredArgsConstructor
@Tag(name = "tbl_listahorario")
public class ListahorarioController {
    private final IListahorarioService listahorarioService;
    private final IConvocatoriaService convocatoriaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los horarios y convocatorias")
    @GetMapping
    public ResponseEntity<List<ListahorarioDTO>> findAll() {
        List<ListahorarioDTO> list = mapperUtil.mapList(listahorarioService.findAll(), ListahorarioDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un horario y una convocatoria")
    @GetMapping("/{id}")
    public ResponseEntity<ListahorarioDTO> findById(@PathVariable("id") Integer id) {
        Listahorario obj = listahorarioService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListahorarioDTO.class));
    }

    @Operation(summary = "Crea una varias lista horario")
    @PostMapping("/crear-bloque")
    public ResponseEntity<ListahorariobloqueDTO> crearBloque(@Valid @RequestBody ListahorariobloqueDTO dto) {
        listahorarioService.guardarBloqueHorarios(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza la lista de horarios de una convocatoria")
    @PutMapping("/actualizar-bloque/{idConvocatoria}")
    public ResponseEntity<ListahorariobloqueDTO> actualizarBloque(@PathVariable Integer idConvocatoria, @Valid @RequestBody ListahorariobloqueDTO dto) {

        listahorarioService.actualizarBloqueHorarios(idConvocatoria, dto);

        return ResponseEntity.ok(dto);
    }
    @Operation(summary = "Eliminar una convocatoria y sus listahorarios")
    @DeleteMapping("/eliminar/{idConvocatoria}")
    public ResponseEntity<Void> eliminarConvocatoria(@PathVariable Integer idConvocatoria) {

        listahorarioService.eliminarConvocatoria(idConvocatoria);

        return ResponseEntity.noContent().build();
    }
}
