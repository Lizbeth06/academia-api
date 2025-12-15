package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TiposeguroDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tiposeguro;
import academiaapi.ipd.gob.pe.academiaapi.service.ITiposeguroService;
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
@RequestMapping("/api/tiposeguro")
@RequiredArgsConstructor
@Tag(name = "tbl_tiposeguro")
public class TiposeguroController {
    private final ITiposeguroService tiposeguroService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los tipos de seguro")
    @GetMapping
    public ResponseEntity<List<TiposeguroDTO>> findAll() {
        List<TiposeguroDTO> list = mapperUtil.mapList(tiposeguroService.findAll(), TiposeguroDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un seguro")
    @GetMapping("/{id}")
    public ResponseEntity<TiposeguroDTO> findById(@PathVariable("id") Integer id) {
        Tiposeguro obj = tiposeguroService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TiposeguroDTO.class));
    }

    @Operation(summary = "Crea un registro de seguro")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TiposeguroDTO dto) {
        Tiposeguro obj = tiposeguroService.save(mapperUtil.map(dto, Tiposeguro.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTiposeguro()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un registro de un seguro")
    @PutMapping("/{id}")
    public ResponseEntity<TiposeguroDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TiposeguroDTO dto) {
        dto.setIdTiposeguro(id);
        Tiposeguro obj = tiposeguroService.update(id, mapperUtil.map(dto, Tiposeguro.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TiposeguroDTO.class));
    }

    @Operation(summary = "Elimina un seguro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tiposeguroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
