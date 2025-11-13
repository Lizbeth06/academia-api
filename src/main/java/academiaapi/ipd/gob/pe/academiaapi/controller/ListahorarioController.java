package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import academiaapi.ipd.gob.pe.academiaapi.service.IListahorarioService;
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
@RequestMapping("/api/listahorario")
@RequiredArgsConstructor
public class ListahorarioController {
    private final IListahorarioService listahorarioService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ListahorarioDTO>> findAll() {
        List<ListahorarioDTO> list = mapperUtil.mapList(listahorarioService.findAll(), ListahorarioDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListahorarioDTO> findById(@PathVariable("id") Integer id) {
        Listahorario obj = listahorarioService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListahorarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ListahorarioDTO dto) {
        Listahorario obj = listahorarioService.save(mapperUtil.map(dto, Listahorario.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdListahorario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListahorarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ListahorarioDTO dto) {
        dto.setIdListahorario(id);
        Listahorario obj = listahorarioService.update(id, mapperUtil.map(dto, Listahorario.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListahorarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        listahorarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
