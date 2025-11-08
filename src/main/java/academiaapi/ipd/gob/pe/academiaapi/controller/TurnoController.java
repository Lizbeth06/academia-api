package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TurnoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
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
@RequestMapping("/api/turno")
@RequiredArgsConstructor
public class TurnoController {
    private final ITurnoService turnoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> findAll() {
        List<TurnoDTO> list = mapperUtil.mapList(turnoService.findAll(), TurnoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> findById(@PathVariable("id") Integer id) {
        Turno obj = turnoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TurnoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TurnoDTO dto) {
        Turno obj = turnoService.save(mapperUtil.map(dto, Turno.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTurno()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TurnoDTO dto) {
        dto.setIdTurno(id);
        Turno obj = turnoService.update(id, mapperUtil.map(dto, Turno.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TurnoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        turnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
