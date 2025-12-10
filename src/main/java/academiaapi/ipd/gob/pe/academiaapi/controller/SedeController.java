package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.SedeDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.UbigeoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.service.ISedeService;
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
import java.util.Map;

@Controller
@RequestMapping("/api/sede")
@RequiredArgsConstructor
@Tag(name="tbl_sede")
public class SedeController {
    private final ISedeService sedeService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todas las sedes")
    @GetMapping
    public ResponseEntity<List<SedeDTO>> findAll() {
        List<SedeDTO> list = mapperUtil.mapList(sedeService.findAll(), SedeDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una sede")
    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> findById(@PathVariable("id") Integer id) {
        Sede obj = sedeService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, SedeDTO.class));
    }

    @Operation(summary = "Agrega una sede")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SedeDTO dto) {
        Sede obj = sedeService.save(mapperUtil.map(dto, Sede.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSede()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una sede")
    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SedeDTO dto) {
        dto.setIdSede(id);
        Sede obj = sedeService.update(id, mapperUtil.map(dto, Sede.class));
        return ResponseEntity.ok(mapperUtil.map(obj, SedeDTO.class));
    }

    @Operation(summary = "Elimina una sede")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        sedeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista sede por ubicacion")
    @GetMapping("/ubicacion")
    public ResponseEntity<List<SedeDTO>> getSedexubicacion(@RequestParam String ubicacion) {
        List<SedeDTO> sedes = mapperUtil.mapList(sedeService.getSedes(ubicacion),SedeDTO.class) ;
        return ResponseEntity.ok(sedes);
    }
}
