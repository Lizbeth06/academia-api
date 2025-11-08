package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoCargoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.TipoCargo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoCargoService;
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
@RequestMapping("/api/tipoTipoCargo")
@RequiredArgsConstructor
public class TipoCargoController {
    private final ITipoCargoService tipoTipoCargoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TipoCargoDTO>> findAll() {
        List<TipoCargoDTO> list = mapperUtil.mapList(tipoTipoCargoService.findAll(), TipoCargoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCargoDTO> findById(@PathVariable("id") Integer id) {
        TipoCargo obj = tipoTipoCargoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoCargoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipoCargoDTO dto) {
        TipoCargo obj = tipoTipoCargoService.save(mapperUtil.map(dto, TipoCargo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoCargo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCargoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TipoCargoDTO dto) {
        dto.setIdTipoCargo(id);
        TipoCargo obj = tipoTipoCargoService.update(id, mapperUtil.map(dto, TipoCargo.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TipoCargoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoTipoCargoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
