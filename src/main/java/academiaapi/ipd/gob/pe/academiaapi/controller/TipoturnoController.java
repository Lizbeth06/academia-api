package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoturnoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoturno;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoturnoService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tipoturno")
@RequiredArgsConstructor
@Tag(name = "tbl_tipoturno")
public class TipoturnoController {
    private final ITipoturnoService tipoturnoService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los tipos de turno")
    @GetMapping
    public ResponseEntity<List<TipoturnoDTO>> findAll() {
        List<TipoturnoDTO> list = mapperUtil.mapList(tipoturnoService.findAll(), TipoturnoDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un tipo de turno")
    @GetMapping("/{id}")
    public ResponseEntity<TipoturnoDTO> findById(@PathVariable("id") Integer id) {
        Tipoturno obj = tipoturnoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoturnoDTO.class));
    }
}
