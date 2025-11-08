package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoasistenciaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoasistencia;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoasistenciaService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/tipoasistencia")
@RequiredArgsConstructor
public class TipoasistenciaController {
    private final ITipoasistenciaService tipoasistenciaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TipoasistenciaDTO>> findAll() {
        List<TipoasistenciaDTO> list = mapperUtil.mapList(tipoasistenciaService.findAll(), TipoasistenciaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoasistenciaDTO> findById(@PathVariable("id") Integer id) {
        Tipoasistencia obj = tipoasistenciaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoasistenciaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipoasistenciaDTO dto) {
        Tipoasistencia obj = tipoasistenciaService.save(mapperUtil.map(dto, Tipoasistencia.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoasistencia()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoasistenciaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TipoasistenciaDTO dto) {
        dto.setIdTipoasistencia(id);
        Tipoasistencia obj = tipoasistenciaService.update(id, mapperUtil.map(dto, Tipoasistencia.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TipoasistenciaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoasistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
