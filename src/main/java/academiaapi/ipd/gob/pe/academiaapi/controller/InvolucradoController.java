package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.InvolucradoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Involucrado;
import academiaapi.ipd.gob.pe.academiaapi.service.IInvolucradoService;
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
@RequestMapping("/api/involucrado")
@RequiredArgsConstructor
public class InvolucradoController {
    private final IInvolucradoService involucradoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<InvolucradoDTO>> findAll() {
        List<InvolucradoDTO> list = mapperUtil.mapList(involucradoService.findAll(), InvolucradoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvolucradoDTO> findById(@PathVariable("id") Integer id) {
        Involucrado obj = involucradoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, InvolucradoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InvolucradoDTO dto) {
        Involucrado obj = involucradoService.save(mapperUtil.map(dto, Involucrado.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInvolucrado()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvolucradoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InvolucradoDTO dto) {
        dto.setIdInvolucrado(id);
        Involucrado obj = involucradoService.update(id, mapperUtil.map(dto, Involucrado.class));
        return ResponseEntity.ok(mapperUtil.map(obj, InvolucradoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        involucradoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
