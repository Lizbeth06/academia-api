package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipodocumentoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipodocumento;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipodocumentoService;
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
@RequestMapping("/api/tipodocumento")
@RequiredArgsConstructor
@Tag(name = "tbl_tipodocumento")
public class TipodocumentoController {
    private final ITipodocumentoService tipodocumentoService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo el tipo de documento")
    @GetMapping
    public ResponseEntity<List<TipodocumentoDTO>> findAll() {
        List<TipodocumentoDTO> list=mapperUtil.mapList(tipodocumentoService.findAll(), TipodocumentoDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un tipo de documento")
    @GetMapping("/{id}")
    public ResponseEntity<TipodocumentoDTO> findById(@PathVariable("id") Integer id) {
        Tipodocumento obj=tipodocumentoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipodocumentoDTO.class));
    }

    @Operation(summary = "Agrega un tipo de documento")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipodocumentoDTO dto) {
        Tipodocumento obj=tipodocumentoService.save(mapperUtil.map(dto, Tipodocumento.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoDocumento()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un tipo de documento")
    @PutMapping("/{id}")
    public ResponseEntity<TipodocumentoDTO> update(@Valid  @PathVariable("id") Integer id, @RequestBody TipodocumentoDTO dto) {
        dto.setIdTipoDocumento(id);
        Tipodocumento obj=tipodocumentoService.update(id,mapperUtil.map(dto, Tipodocumento.class));
        return ResponseEntity.ok(mapperUtil.map(obj,TipodocumentoDTO.class));
    }

    @Operation(summary = "Elimina un tipo de documento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipodocumentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
