package academiaapi.ipd.gob.pe.academiaapi.controller;


import academiaapi.ipd.gob.pe.academiaapi.dto.MenurolDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Menurol;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenurolService;
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
@RequestMapping("/api/menurol")
@RequiredArgsConstructor
@Tag(name = "tbl_menurol")
public class MenurolController {
    private final IMenurolService menurolService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo el menu rol")
    @GetMapping
    public ResponseEntity<List<MenurolDTO>> findAll() {
        List<MenurolDTO> list=mapperUtil.mapList(menurolService.findAll(), MenurolDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un menu rol")
    @GetMapping("/{id}")
    public ResponseEntity<MenurolDTO> findById(@PathVariable Integer id) {
        Menurol obj=menurolService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, MenurolDTO.class));
    }

    @Operation(summary = "Agrega un menu rol")
    @PutMapping("/{id}")
    public ResponseEntity<MenurolDTO> update(@Valid @PathVariable Integer id, @RequestBody MenurolDTO dto) {
        dto.setIdMenuRol(id);
        Menurol obj=menurolService.update(id, mapperUtil.map(dto,Menurol.class));
        return ResponseEntity.ok(mapperUtil.map(obj, MenurolDTO.class));
    }

    @Operation(summary = "Actualiza un menu rol")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MenurolDTO dto) {
        Menurol obj= menurolService.save(mapperUtil.map(dto,Menurol.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenuRol()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Elimina un menu rol")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        menurolService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
