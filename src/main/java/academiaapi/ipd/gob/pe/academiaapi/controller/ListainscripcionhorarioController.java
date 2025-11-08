package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ListainscripcionhorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listainscripcionhorario;
import academiaapi.ipd.gob.pe.academiaapi.service.IListainscripcionhorarioService;
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
@RequestMapping("/api/listainscripcionhorario")
@RequiredArgsConstructor
public class ListainscripcionhorarioController {
    private final IListainscripcionhorarioService listainscripcionhorarioService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ListainscripcionhorarioDTO>> findAll() {
        List<ListainscripcionhorarioDTO> list = mapperUtil.mapList(listainscripcionhorarioService.findAll(), ListainscripcionhorarioDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListainscripcionhorarioDTO> findById(@PathVariable("id") Integer id) {
        Listainscripcionhorario obj = listainscripcionhorarioService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListainscripcionhorarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ListainscripcionhorarioDTO dto) {
        Listainscripcionhorario obj = listainscripcionhorarioService.save(mapperUtil.map(dto, Listainscripcionhorario.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdListainscripcionhorario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListainscripcionhorarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ListainscripcionhorarioDTO dto) {
        dto.setIdListainscripcionhorario(id);
        Listainscripcionhorario obj = listainscripcionhorarioService.update(id, mapperUtil.map(dto, Listainscripcionhorario.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListainscripcionhorarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        listainscripcionhorarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
