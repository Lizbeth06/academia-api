package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.CargoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Cargo;
import academiaapi.ipd.gob.pe.academiaapi.service.ICargoService;
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
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
public class CargoController {
    private final ICargoService cargoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> findAll() {
        List<CargoDTO> list = mapperUtil.mapList(cargoService.findAll(), CargoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> findById(@PathVariable("id") Integer id) {
        Cargo obj = cargoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, CargoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CargoDTO dto) {
        Cargo obj = cargoService.save(mapperUtil.map(dto, Cargo.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCargo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CargoDTO dto) {
        dto.setIdCargo(id);
        Cargo obj = cargoService.update(id, mapperUtil.map(dto, Cargo.class));
        return ResponseEntity.ok(mapperUtil.map(obj, CargoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        cargoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
