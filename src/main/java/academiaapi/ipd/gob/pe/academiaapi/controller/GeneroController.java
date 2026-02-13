package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.GeneroDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Genero;
import academiaapi.ipd.gob.pe.academiaapi.service.IGeneroService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/genero")
@RequiredArgsConstructor
public class GeneroController {
    private final IGeneroService generoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> findAll() {
        List<GeneroDTO> list = mapperUtil.mapList(generoService.findAll(), GeneroDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> findById(@PathVariable("id") Integer id) {
        Genero obj = generoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, GeneroDTO.class));
    }

}
