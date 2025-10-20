package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.NiveleducacionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Niveleducacion;
import academiaapi.ipd.gob.pe.academiaapi.service.INiveleducacionService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/niveleducacion")
@RequiredArgsConstructor
@Tag(name = "tbl_niveleducacion")
public class NiveleducacionController {
    private final INiveleducacionService niveleducacionService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo el nivel educativo")
    @GetMapping
    public ResponseEntity<List<NiveleducacionDTO>> findAll() {
        List<NiveleducacionDTO> list=mapperUtil.mapList(niveleducacionService.findAll(), NiveleducacionDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un nivel educativo")
    @GetMapping("/{id}")
    public ResponseEntity<NiveleducacionDTO> findById(@PathVariable("id") Integer id) {
        Niveleducacion obj=niveleducacionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, NiveleducacionDTO.class));
    }

    @Operation(summary = "Agrega un nivel educativo")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody NiveleducacionDTO dto) {
        Niveleducacion obj=niveleducacionService.save(mapperUtil.map(dto, Niveleducacion.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdNivelEducacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un nivel educativo")
    @PutMapping("/{id}")
    public ResponseEntity<NiveleducacionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody NiveleducacionDTO dto) {
        dto.setIdNivelEducacion(id);
        Niveleducacion obj=niveleducacionService.update(id, mapperUtil.map(dto, Niveleducacion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, NiveleducacionDTO.class));
    }

    @Operation(summary = "Elimina un nivel educativo")
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        niveleducacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
