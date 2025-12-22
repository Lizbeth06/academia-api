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
        ConvocatoriaDTO convocatoriaDTO = dto.getConvocatoria();
        Temporada temporada = new Temporada();
        temporada.setIdTemporada(dto.getConvocatoria().getTemporada().getIdTemporada());

        Convocatoria convocatoria = new Convocatoria();
        convocatoria.setTitulo(convocatoriaDTO.getTitulo());
        convocatoria.setSubtitulo(convocatoriaDTO.getSubtitulo());
        convocatoria.setDescripcion(convocatoriaDTO.getDescripcion());
        convocatoria.setTemporada(temporada);
        convocatoria.setUrlImagen(convocatoriaDTO.getUrlImagen());
        convocatoria.setEstado("1");
        convocatoria.setFechacreada(java.time.LocalDateTime.now());
        convocatoria = convocatoriaService.save(convocatoria);

        for (ListahorarioDTO horarioDTO : dto.getListaHorarios()) {
            Listahorario entity = mapperUtil.map(horarioDTO, Listahorario.class);
            entity.setConvocatoria(convocatoria);

            if (horarioDTO.getHorario() != null && horarioDTO.getHorario().getIdHorario() != null) {
                Horario horario = new Horario();
                horario.setIdHorario(horarioDTO.getHorario().getIdHorario());
                entity.setHorario(horario);
            }
            listahorarioService.save(entity);
        }

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ListahorarioDTO> updateMany(@Valid @PathVariable("id") Integer id, @RequestBody ListahorarioDTO dto) {
        dto.setIdListahorario(id);
        Listahorario obj = listahorarioService.update(id, mapperUtil.map(dto, Listahorario.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListahorarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        listahorarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
