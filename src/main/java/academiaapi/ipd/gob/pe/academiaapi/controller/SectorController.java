package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.SectorDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Sector;
import academiaapi.ipd.gob.pe.academiaapi.service.ISectorService;
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
@RequestMapping("/api/sector")
@RequiredArgsConstructor
@Tag(name = "tbl_sector")
public class SectorController {
    private final ISectorService sectorService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los sectores")
    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll() {
        List<SectorDTO> list = mapperUtil.mapList(sectorService.findAll(), SectorDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "List un rol")
    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> findById(@PathVariable("id") Integer id) {
        Sector obj = sectorService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, SectorDTO.class));
    }

    @Operation(summary = "Crea un sector")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SectorDTO dto) {
        Sector obj = sectorService.save(mapperUtil.map(dto, Sector.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSector()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un sector")
    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SectorDTO dto) {
        dto.setIdSector(id);
        Sector obj = sectorService.update(id, mapperUtil.map(dto, Sector.class));
        return ResponseEntity.ok(mapperUtil.map(obj, SectorDTO.class));
    }

    @Operation(summary = "Elimina un sector")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        sectorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
