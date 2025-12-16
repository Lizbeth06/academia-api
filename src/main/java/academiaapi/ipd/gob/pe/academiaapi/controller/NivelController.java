package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AnioDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.NivelDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Nivel;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import academiaapi.ipd.gob.pe.academiaapi.service.INivelService;
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
@RequestMapping("/api/nivel")
@RequiredArgsConstructor
public class NivelController {
    private final INivelService nivelService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<NivelDTO>> findAll() {
        List<NivelDTO> list = mapperUtil.mapList(nivelService.findAll(), NivelDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelDTO> findById(@PathVariable("id") Integer id) {
        Nivel obj = nivelService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, NivelDTO.class));
    }
}
