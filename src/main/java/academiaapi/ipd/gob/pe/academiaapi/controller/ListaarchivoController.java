package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ListaarchivoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listaarchivo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListaarchivoService;
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
@RequestMapping("/api/listaarchivo")
@RequiredArgsConstructor
public class ListaarchivoController {
    private final IListaarchivoService listaarchivoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ListaarchivoDTO>> findAll() {
        List<ListaarchivoDTO> list = mapperUtil.mapList(listaarchivoService.findAll(), ListaarchivoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaarchivoDTO> findById(@PathVariable("id") Integer id) {
        Listaarchivo obj = listaarchivoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListaarchivoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ListaarchivoDTO dto) {
        Listaarchivo obj = listaarchivoService.save(mapperUtil.map(dto, Listaarchivo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdListaarchivo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaarchivoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ListaarchivoDTO dto) {
        dto.setIdListaarchivo(id);
        Listaarchivo obj = listaarchivoService.update(id, mapperUtil.map(dto, Listaarchivo.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListaarchivoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        listaarchivoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
