package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.CriterioparticipacionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Criterioparticipacion;
import academiaapi.ipd.gob.pe.academiaapi.service.ICriterioparticipacionService;
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
@RequestMapping("/api/criterioparticipacion")
@RequiredArgsConstructor
public class CriterioparticipacionController {
    private final ICriterioparticipacionService criterioparticipacionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<CriterioparticipacionDTO>> findAll() {
        List<CriterioparticipacionDTO> list = mapperUtil.mapList(criterioparticipacionService.findAll(), CriterioparticipacionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriterioparticipacionDTO> findById(@PathVariable("id") Integer id) {
        Criterioparticipacion obj = criterioparticipacionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CriterioparticipacionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CriterioparticipacionDTO dto) {
        Criterioparticipacion obj = criterioparticipacionService.save(mapperUtil.map(dto, Criterioparticipacion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCriterioparticipacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriterioparticipacionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CriterioparticipacionDTO dto) {
        dto.setIdCriterioparticipacion(id);
        Criterioparticipacion obj = criterioparticipacionService.update(id, mapperUtil.map(dto, Criterioparticipacion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, CriterioparticipacionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        criterioparticipacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
