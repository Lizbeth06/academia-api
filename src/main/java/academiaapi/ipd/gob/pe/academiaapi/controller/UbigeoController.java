package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TrabajadorDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.UbigeoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;
import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import academiaapi.ipd.gob.pe.academiaapi.service.IUbigeoService;
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
@RequestMapping("/api/ubigeo")
@RequiredArgsConstructor
@Tag(name = "tbl_ubigeo")
public class UbigeoController {
    private final IUbigeoService ubigeoService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista un ubigeo")
    @GetMapping("/{id}")
    public ResponseEntity<UbigeoDTO> findById(@PathVariable Integer id){
        Ubigeo obj=ubigeoService.findById(id);
        return ResponseEntity.ok().body(mapperUtil.map(obj, UbigeoDTO.class));
    }

    @Operation(summary = "Lista departamentos")
    @GetMapping("/departamentos")
    public ResponseEntity<List<UbigeoDTO>> getDepartments() {
        List<UbigeoDTO> departments = mapperUtil.mapList(ubigeoService.getDepartments(), UbigeoDTO.class);
        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Lista provincias")
    @GetMapping("/provincias/{department}")
    public ResponseEntity<List<UbigeoDTO>> getProvincias(@PathVariable String department) {
        List<UbigeoDTO> provincias = mapperUtil.mapList(ubigeoService.getProviciasByDepartment(department),UbigeoDTO.class) ;
        return ResponseEntity.ok(provincias);
    }

    @Operation(summary = "Lista distritos")
    @GetMapping("/distritos")
    public ResponseEntity<List<UbigeoDTO>> getDistritos(
            @RequestParam String departamento,
            @RequestParam String provincia)  {
        List<UbigeoDTO> ubigeos = mapperUtil.mapList(ubigeoService.getDistritos(departamento, provincia),UbigeoDTO.class);
        return ResponseEntity.ok(ubigeos);
    }

}