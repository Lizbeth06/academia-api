package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.CategoriaedadDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Categoriaedad;
import academiaapi.ipd.gob.pe.academiaapi.service.ICategoriaedadService;
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
@RequestMapping("/api/categoriaedad")
@RequiredArgsConstructor
public class CategoriaedadController {
    private final ICategoriaedadService categoriaedadService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<CategoriaedadDTO>> findAll() {
        List<CategoriaedadDTO> list = mapperUtil.mapList(categoriaedadService.findAll(), CategoriaedadDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaedadDTO> findById(@PathVariable("id") Integer id) {
        Categoriaedad obj = categoriaedadService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CategoriaedadDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaedadDTO dto) {
        Categoriaedad obj = categoriaedadService.save(mapperUtil.map(dto, Categoriaedad.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoriaedad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaedadDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoriaedadDTO dto) {
        dto.setIdCategoriaedad(id);
        Categoriaedad obj = categoriaedadService.update(id, mapperUtil.map(dto, Categoriaedad.class));
        return ResponseEntity.ok(mapperUtil.map(obj, CategoriaedadDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        categoriaedadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
