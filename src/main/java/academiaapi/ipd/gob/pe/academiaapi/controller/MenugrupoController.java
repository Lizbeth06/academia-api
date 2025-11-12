package academiaapi.ipd.gob.pe.academiaapi.controller;


import academiaapi.ipd.gob.pe.academiaapi.dto.MenugrupoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Menugrupo;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenugrupoService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/menugrupo")
@RequiredArgsConstructor
@Tag(name = "tbl_menugrupo")
public class MenugrupoController {
    private final IMenugrupoService menugrupoService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo los menugrupos")
    @GetMapping
    public ResponseEntity<List<MenugrupoDTO>> findAll() {
        List<MenugrupoDTO> list=mapperUtil.mapList(menugrupoService.findAll(), MenugrupoDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un menugrupo")
    @GetMapping("/{id}")
    public ResponseEntity<MenugrupoDTO> findById(@PathVariable Integer id) {
        Menugrupo obj=menugrupoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, MenugrupoDTO.class));
    }

    @Operation(summary = "Agrega un menugrupo")
    @PutMapping("/{id}")
    public ResponseEntity<MenugrupoDTO> update(@Valid @PathVariable Integer id, @RequestBody MenugrupoDTO dto) {
        dto.setIdMenugrupo(id);
        Menugrupo obj=menugrupoService.update(id, mapperUtil.map(dto, Menugrupo.class));
        return ResponseEntity.ok(mapperUtil.map(obj, MenugrupoDTO.class));
    }

    @Operation(summary = "Actualiza un menugrupo")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MenugrupoDTO dto) {
        Menugrupo obj = menugrupoService.save(mapperUtil.map(dto, Menugrupo.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenugrupo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Elimina un menugrupo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        menugrupoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
