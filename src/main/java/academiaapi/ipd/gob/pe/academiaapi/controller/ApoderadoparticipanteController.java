package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ApoderadoparticipanteDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoparticipanteService;
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
@RequestMapping("/api/apoderadoparticipante")
@RequiredArgsConstructor
public class ApoderadoparticipanteController {
    private final IApoderadoparticipanteService apoderadoparticipanteService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ApoderadoparticipanteDTO>> findAll() {
        List<ApoderadoparticipanteDTO> list = mapperUtil.mapList(apoderadoparticipanteService.findAll(), ApoderadoparticipanteDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoparticipanteDTO> findById(@PathVariable("id") Integer id) {
        Apoderadoparticipante obj = apoderadoparticipanteService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ApoderadoparticipanteDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ApoderadoparticipanteDTO dto) {
        Apoderadoparticipante obj = apoderadoparticipanteService.save(mapperUtil.map(dto, Apoderadoparticipante.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdApoderadoparticipante()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoparticipanteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ApoderadoparticipanteDTO dto) {
        dto.setIdApoderadoparticipante(id);
        Apoderadoparticipante obj = apoderadoparticipanteService.update(id, mapperUtil.map(dto, Apoderadoparticipante.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ApoderadoparticipanteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        apoderadoparticipanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
