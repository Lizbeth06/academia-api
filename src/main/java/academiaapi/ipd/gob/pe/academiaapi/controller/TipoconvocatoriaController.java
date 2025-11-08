package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoconvocatoriaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoconvocatoria;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoconvocatoriaService;
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
@RequestMapping("/api/tipoconvocatoria")
@RequiredArgsConstructor
public class TipoconvocatoriaController {
    private final ITipoconvocatoriaService tipoconvocatoriaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TipoconvocatoriaDTO>> findAll() {
        List<TipoconvocatoriaDTO> list = mapperUtil.mapList(tipoconvocatoriaService.findAll(), TipoconvocatoriaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoconvocatoriaDTO> findById(@PathVariable("id") Integer id) {
        Tipoconvocatoria obj = tipoconvocatoriaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoconvocatoriaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipoconvocatoriaDTO dto) {
        Tipoconvocatoria obj = tipoconvocatoriaService.save(mapperUtil.map(dto, Tipoconvocatoria.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoconvocatoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoconvocatoriaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TipoconvocatoriaDTO dto) {
        dto.setIdTipoconvocatoria(id);
        Tipoconvocatoria obj = tipoconvocatoriaService.update(id, mapperUtil.map(dto, Tipoconvocatoria.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TipoconvocatoriaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoconvocatoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
