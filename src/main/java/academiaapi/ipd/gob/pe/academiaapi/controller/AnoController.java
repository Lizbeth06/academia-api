package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AnoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Ano;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnoService;
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
@RequestMapping("/api/ano")
@RequiredArgsConstructor
public class AnoController {
    private final IAnoService anoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<AnoDTO>> findAll() {
        List<AnoDTO> list = mapperUtil.mapList(anoService.findAll(), AnoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnoDTO> findById(@PathVariable("id") Integer id) {
        Ano obj = anoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, AnoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AnoDTO dto) {
        Ano obj = anoService.save(mapperUtil.map(dto, Ano.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAno()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody AnoDTO dto) {
        dto.setIdAno(id);
        Ano obj = anoService.update(id, mapperUtil.map(dto, Ano.class));
        return ResponseEntity.ok(mapperUtil.map(obj, AnoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        anoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
