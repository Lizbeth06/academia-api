package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TiporelacionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tiporelacion;
import academiaapi.ipd.gob.pe.academiaapi.service.ITiporelacionService;
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
@RequestMapping("/api/tiporelacion")
@RequiredArgsConstructor
public class TiporelacionController {
    private final ITiporelacionService tiporelacionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TiporelacionDTO>> findAll() {
        List<TiporelacionDTO> list = mapperUtil.mapList(tiporelacionService.findAll(), TiporelacionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiporelacionDTO> findById(@PathVariable("id") Integer id) {
        Tiporelacion obj = tiporelacionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TiporelacionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TiporelacionDTO dto) {
        Tiporelacion obj = tiporelacionService.save(mapperUtil.map(dto, Tiporelacion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTiporelacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiporelacionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TiporelacionDTO dto) {
        dto.setIdTiporelacion(id);
        Tiporelacion obj = tiporelacionService.update(id, mapperUtil.map(dto, Tiporelacion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TiporelacionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tiporelacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
