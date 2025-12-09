package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TurnoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
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
@RequestMapping("/api/turno")
@RequiredArgsConstructor
@Tag(name = "tbl_turno")
public class TurnoController {
    private final ITurnoService turnoService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los turnos")
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> findAll() {
        List<TurnoDTO> list = mapperUtil.mapList(turnoService.findAll(), TurnoDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un turno")
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> findById(@PathVariable("id") Integer id) {
        Turno obj = turnoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TurnoDTO.class));
    }

    @Operation(summary = "Agrega un nuevo turno")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TurnoDTO dto) {
        Turno obj = turnoService.save(mapperUtil.map(dto, Turno.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTurno()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un turno")
    @PutMapping("/{id}")
    public ResponseEntity<TurnoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TurnoDTO dto) {
        dto.setIdTurno(id);
        Turno obj = turnoService.update(id, mapperUtil.map(dto, Turno.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TurnoDTO.class));
    }

    @Operation(summary = "Elimina un turno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Turno turno = turnoService.findById(id);
        if(turno==null){
            throw new EntityNotFoundException("Turno no encontrado");
        }
        turno.setEstado("0");
        turnoService.save(turno);
        return ResponseEntity.noContent().build();
    }
}
