package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ArchivoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Archivo;
import academiaapi.ipd.gob.pe.academiaapi.service.IArchivoService;
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
@RequestMapping("/api/archivo")
@RequiredArgsConstructor
public class ArchivoController {
    private final IArchivoService archivoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ArchivoDTO>> findAll() {
        List<ArchivoDTO> list = mapperUtil.mapList(archivoService.findAll(), ArchivoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoDTO> findById(@PathVariable("id") Integer id) {
        Archivo obj = archivoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ArchivoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ArchivoDTO dto) {
        Archivo obj = archivoService.save(mapperUtil.map(dto, Archivo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdArchivo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArchivoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ArchivoDTO dto) {
        dto.setIdArchivo(id);
        Archivo obj = archivoService.update(id, mapperUtil.map(dto, Archivo.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ArchivoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        archivoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
