package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TrabajadorDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;
import academiaapi.ipd.gob.pe.academiaapi.service.ITrabajadorService;
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
@RequestMapping("api/trabajador")
@RequiredArgsConstructor
@Tag(name = "tbl_trabajador")
public class TrabajadorController {
    private final ITrabajadorService trabajadorService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo el trabajador")
    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> findAll(){
        List<TrabajadorDTO> list=mapperUtil.mapList(trabajadorService.findAll(), TrabajadorDTO.class);
        //sino funciona borrar body(list) --> ok(list)
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Lista un trabajador")
    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> findById(@PathVariable Integer id){
        Trabajador obj=trabajadorService.findById(id);
        return ResponseEntity.ok().body(mapperUtil.map(obj, TrabajadorDTO.class));
    }



    @Operation(summary = "Agrega un trabajador")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TrabajadorDTO dto){
        Trabajador obj=trabajadorService.save(mapperUtil.map(dto, Trabajador.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTrabajador()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un trabajador")
    @PutMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TrabajadorDTO dto){
        dto.setIdTrabajador(id);
        Trabajador obj=trabajadorService.update(id, mapperUtil.map(dto, Trabajador.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TrabajadorDTO.class));
    }

    @Operation(summary = "Elimina un trabajador")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        trabajadorService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
