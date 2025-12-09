package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TemporadaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.service.ITemporadaService;
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
@RequestMapping("/api/temporada")
@RequiredArgsConstructor
@Tag(name = "tbl_temporada")
public class TemporadaController {
    private final ITemporadaService temporadaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todas las temporadas")
    @GetMapping
    public ResponseEntity<List<TemporadaDTO>> findAll() {
        List<TemporadaDTO> list = mapperUtil.mapList(temporadaService.findAll(), TemporadaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una temporada")
    @GetMapping("/{id}")
    public ResponseEntity<TemporadaDTO> findById(@PathVariable("id") Integer id) {
        Temporada obj = temporadaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TemporadaDTO.class));
    }

    @Operation(summary = "Agrega una temporada")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TemporadaDTO dto) {
        Temporada obj = temporadaService.save(mapperUtil.map(dto, Temporada.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTemporada()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una temporada")
    @PutMapping("/{id}")
    public ResponseEntity<TemporadaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TemporadaDTO dto) {
        dto.setIdTemporada(id);
        Temporada obj = temporadaService.update(id, mapperUtil.map(dto, Temporada.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TemporadaDTO.class));
    }

    @Operation(summary = "Elimina una temporada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Temporada temporada = temporadaService.findById(id);
        if(temporada==null){
            throw new EntityNotFoundException("Temporada no encontrado");
        }
        temporada.setEstado("0");
        temporadaService.save(temporada);
        return ResponseEntity.noContent().build();
    }
}
