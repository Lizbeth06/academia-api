package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ConvocatoriaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.service.IConvocatoriaService;
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
@RequestMapping("/api/convocatoria")
@RequiredArgsConstructor
public class ConvocatoriaController {
    private final IConvocatoriaService convocatoriaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ConvocatoriaDTO>> findAll() {
        List<ConvocatoriaDTO> list = mapperUtil.mapList(convocatoriaService.findAll(), ConvocatoriaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvocatoriaDTO> findById(@PathVariable("id") Integer id) {
        Convocatoria obj = convocatoriaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ConvocatoriaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConvocatoriaDTO dto) {
        Convocatoria obj = convocatoriaService.save(mapperUtil.map(dto, Convocatoria.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConvocatoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvocatoriaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ConvocatoriaDTO dto) {
        dto.setIdConvocatoria(id);
        Convocatoria obj = convocatoriaService.update(id, mapperUtil.map(dto, Convocatoria.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ConvocatoriaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        convocatoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
