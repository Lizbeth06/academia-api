package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ListadisciplinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.service.IListadisciplinaService;
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
@RequestMapping("/api/listadisciplina")
@RequiredArgsConstructor
public class ListadisciplinaController {
    private final IListadisciplinaService listadisciplinaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ListadisciplinaDTO>> findAll() {
        List<ListadisciplinaDTO> list = mapperUtil.mapList(listadisciplinaService.findAll(), ListadisciplinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadisciplinaDTO> findById(@PathVariable("id") Integer id) {
        Listadisciplina obj = listadisciplinaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListadisciplinaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ListadisciplinaDTO dto) {
        Listadisciplina obj = listadisciplinaService.save(mapperUtil.map(dto, Listadisciplina.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdListadisciplina()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListadisciplinaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ListadisciplinaDTO dto) {
        dto.setIdListadisciplina(id);
        Listadisciplina obj = listadisciplinaService.update(id, mapperUtil.map(dto, Listadisciplina.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListadisciplinaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        listadisciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
