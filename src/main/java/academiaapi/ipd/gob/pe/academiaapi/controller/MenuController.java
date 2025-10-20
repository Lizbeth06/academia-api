package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.MenuDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Menu;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenuService;
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
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Tag(name = "tbl_menu")
public class MenuController {
    private final IMenuService menuService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo los menus")
    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll() {
        List<MenuDTO> list=mapperUtil.mapList(menuService.findAll(), MenuDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un menu")
    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> findById(@PathVariable Integer id) {
        Menu obj=menuService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, MenuDTO.class));
    }

    @Operation(summary = "Agrega un menu")
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(@Valid @PathVariable Integer id, @RequestBody MenuDTO dto) {
        dto.setIdMenu(id);
        Menu obj=menuService.update(id, mapperUtil.map(dto, Menu.class));
        return ResponseEntity.ok(mapperUtil.map(obj, MenuDTO.class));
    }

    @Operation(summary = "Actualiza un menu")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MenuDTO dto) {
        Menu obj = menuService.save(mapperUtil.map(dto, Menu.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenu()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Elimina un menu")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
