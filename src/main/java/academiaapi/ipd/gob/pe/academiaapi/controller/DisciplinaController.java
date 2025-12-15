package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.DisciplinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.service.IDisciplinaService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/disciplina")
@RequiredArgsConstructor
@Tag(name="tbl_disciplina")
public class DisciplinaController {
    private final IDisciplinaService disciplinaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista disciplinas por sede")
    @GetMapping("/disciplinaxsede/{idsede}")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinasxsede(@PathVariable("idsede") Integer idsede) {
        List<Disciplina> listadodisciplina=disciplinaService.getDisciplinasxidsede(idsede);
        List<DisciplinaDTO> list = mapperUtil.mapList(listadodisciplina, DisciplinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista todas las disciplinas")
    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        List<Disciplina> listaxestado=disciplinaService.findAll().stream().filter(d->"1".equals(d.getEstado())).toList();
        List<DisciplinaDTO> list = mapperUtil.mapList(listaxestado, DisciplinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una disciplina")
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> findById(@PathVariable("id") Integer id) {
        Disciplina obj = disciplinaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, DisciplinaDTO.class));
    }

    @Operation(summary = "Crea una disciplina")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DisciplinaDTO dto) {
        Disciplina obj = disciplinaService.save(mapperUtil.map(dto, Disciplina.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDisciplina()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una disciplina")
    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody DisciplinaDTO dto) {
        dto.setIdDisciplina(id);
        Disciplina obj = disciplinaService.update(id, mapperUtil.map(dto, Disciplina.class));
        return ResponseEntity.ok(mapperUtil.map(obj, DisciplinaDTO.class));
    }

    @Operation(summary = "Elimina una disciplina")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
