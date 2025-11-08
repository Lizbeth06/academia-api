package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.DisciplinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.service.IDisciplinaService;
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
@RequestMapping("/api/disciplina")
@RequiredArgsConstructor
public class DisciplinaController {
    private final IDisciplinaService disciplinaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        List<DisciplinaDTO> list = mapperUtil.mapList(disciplinaService.findAll(), DisciplinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> findById(@PathVariable("id") Integer id) {
        Disciplina obj = disciplinaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, DisciplinaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DisciplinaDTO dto) {
        Disciplina obj = disciplinaService.save(mapperUtil.map(dto, Disciplina.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDisciplina()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody DisciplinaDTO dto) {
        dto.setIdDisciplina(id);
        Disciplina obj = disciplinaService.update(id, mapperUtil.map(dto, Disciplina.class));
        return ResponseEntity.ok(mapperUtil.map(obj, DisciplinaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
