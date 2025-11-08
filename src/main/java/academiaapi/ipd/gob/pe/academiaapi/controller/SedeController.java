package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.SedeDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.service.ISedeService;
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
@RequestMapping("/api/sede")
@RequiredArgsConstructor
public class SedeController {
    private final ISedeService sedeService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<SedeDTO>> findAll() {
        List<SedeDTO> list = mapperUtil.mapList(sedeService.findAll(), SedeDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> findById(@PathVariable("id") Integer id) {
        Sede obj = sedeService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, SedeDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SedeDTO dto) {
        Sede obj = sedeService.save(mapperUtil.map(dto, Sede.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSede()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SedeDTO dto) {
        dto.setIdSede(id);
        Sede obj = sedeService.update(id, mapperUtil.map(dto, Sede.class));
        return ResponseEntity.ok(mapperUtil.map(obj, SedeDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        sedeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
