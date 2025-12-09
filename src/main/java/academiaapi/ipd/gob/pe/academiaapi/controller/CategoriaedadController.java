package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.CategoriaedadDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Categoriaedad;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.service.ICategoriaedadService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
@Tag(name="tbl_categoriaedad")
public class CategoriaedadController {
    private final ICategoriaedadService categoriaedadService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todas las categorias por edad")
    @GetMapping
    public ResponseEntity<List<CategoriaedadDTO>> findAll() {
        List<CategoriaedadDTO> list = mapperUtil.mapList(categoriaedadService.findAll(), CategoriaedadDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una categoria por edad")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaedadDTO> findById(@PathVariable("id") Integer id) {
        Categoriaedad obj = categoriaedadService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CategoriaedadDTO.class));
    }

    @Operation(summary = "Crea una categoria por edad")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaedadDTO dto) {
        Categoriaedad obj = categoriaedadService.save(mapperUtil.map(dto, Categoriaedad.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoriaedad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una categoria por edad")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaedadDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoriaedadDTO dto) {
        dto.setIdCategoriaedad(id);
        Categoriaedad obj = categoriaedadService.update(id, mapperUtil.map(dto, Categoriaedad.class));
        return ResponseEntity.ok(mapperUtil.map(obj, CategoriaedadDTO.class));
    }

    @Operation(summary = "Elimina una categoria por edad")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Categoriaedad categoriaedad = categoriaedadService.findById(id);
        if(categoriaedad==null){
            throw new EntityNotFoundException("Categoria no encontrado");
        }
        categoriaedad.setEstado("0");
        categoriaedadService.save(categoriaedad);
        return ResponseEntity.noContent().build();
    }
}
