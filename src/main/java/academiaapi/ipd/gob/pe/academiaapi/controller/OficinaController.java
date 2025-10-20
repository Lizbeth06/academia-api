package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.OficinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Oficina;
import academiaapi.ipd.gob.pe.academiaapi.service.IOficinaService;
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
@RequestMapping("/api/oficina")
@RequiredArgsConstructor
@Tag(name = "tbl_oficina")
public class OficinaController {
    private final IOficinaService oficinaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista toda la oficina")
    @GetMapping
    public ResponseEntity<List<OficinaDTO>> findAll() {
        List<OficinaDTO> list=mapperUtil.mapList(oficinaService.findAll(), OficinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una oficina")
    @GetMapping("/{id}")
    public ResponseEntity<OficinaDTO> findById(@PathVariable("id") Integer id) {
        Oficina obj=oficinaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, OficinaDTO.class));
    }

    @Operation(summary = "Agrega una oficina")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody OficinaDTO dto) {
        Oficina obj=oficinaService.save(mapperUtil.map(dto, Oficina.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdOficina()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una oficina")
    @PutMapping("/{id}")
    public ResponseEntity<OficinaDTO> update(@Valid @PathVariable("id") Integer id, @Valid @RequestBody OficinaDTO dto) {
        dto.setIdOficina(id);
        Oficina obj=oficinaService.update(id, mapperUtil.map(dto, Oficina.class));
        return ResponseEntity.ok(mapperUtil.map(obj, OficinaDTO.class));
    }

    @Operation(summary = "Elimina una oficina")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        oficinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
