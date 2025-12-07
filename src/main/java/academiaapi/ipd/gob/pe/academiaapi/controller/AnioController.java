package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AnioDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
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
public class AnioController {
    private final IAnioService anoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<AnioDTO>> findAll() {
        List<AnioDTO> list = mapperUtil.mapList(anoService.findAll(), AnioDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnioDTO> findById(@PathVariable("id") Integer id) {
        Anio obj = anoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, AnioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AnioDTO dto) {
        Anio obj = anoService.save(mapperUtil.map(dto, Anio.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAnio()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody AnioDTO dto) {
        dto.setIdAnio(id);
        Anio obj = anoService.update(id, mapperUtil.map(dto, Anio.class));
        return ResponseEntity.ok(mapperUtil.map(obj, AnioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        anoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
