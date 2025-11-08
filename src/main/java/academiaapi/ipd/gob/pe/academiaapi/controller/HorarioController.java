package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.HorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import academiaapi.ipd.gob.pe.academiaapi.service.IHorarioService;
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
@RequestMapping("/api/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final IHorarioService horarioService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<HorarioDTO>> findAll() {
        List<HorarioDTO> list = mapperUtil.mapList(horarioService.findAll(), HorarioDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> findById(@PathVariable("id") Integer id) {
        Horario obj = horarioService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, HorarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody HorarioDTO dto) {
        Horario obj = horarioService.save(mapperUtil.map(dto, Horario.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdHorario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody HorarioDTO dto) {
        dto.setIdHorario(id);
        Horario obj = horarioService.update(id, mapperUtil.map(dto, Horario.class));
        return ResponseEntity.ok(mapperUtil.map(obj, HorarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        horarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
