package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.common.result.FailureResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.IResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.SuccessResult;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListadisciplinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.service.IListadisciplinaService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/listadisciplina")
@RequiredArgsConstructor
@Tag(name = "tbl_listadisciplina")
public class ListadisciplinaController {
    private final IListadisciplinaService listadisciplinaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todas la listadisciplina")
    @GetMapping
    public ResponseEntity<List<ListadisciplinaDTO>> findAll() {
        List<Listadisciplina> listaxestado=listadisciplinaService.findAll().stream().filter(ld->"1".equals(ld.getEstado())).toList();
        List<ListadisciplinaDTO> list = mapperUtil.mapList(listaxestado, ListadisciplinaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una listadisciplina")
    @GetMapping("/{id}")
    public ResponseEntity<ListadisciplinaDTO> findById(@PathVariable("id") Integer id) {
        Listadisciplina obj = listadisciplinaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ListadisciplinaDTO.class));
    }

    @Operation(summary = "Agrega una listadisciplina")
    @PostMapping
    public ResponseEntity<IResult> save(@Valid @RequestBody ListadisciplinaDTO dto) {

        Listadisciplina nuevo = mapperUtil.map(dto, Listadisciplina.class);
        Optional<Listadisciplina> existe=listadisciplinaService.getListadisciplinaxestado(nuevo.getSede().getIdSede(), nuevo.getDisciplina().getIdDisciplina());
        if(existe.isPresent()){
            Listadisciplina registro=existe.get();
            if ("1".equals(registro.getEstado())) {
                return ResponseEntity.status(400).body(new FailureResult<>(400, "La disciplina ya existe"));
            }
            if ("0".equals(registro.getEstado())) {
                registro.setEstado("1");
                Listadisciplina guardado = listadisciplinaService.save(registro);
                return ResponseEntity
                        .created(ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(guardado.getIdListadisciplina())
                                .toUri())
                        .body(new SuccessResult<>(guardado));
            }
        }
        nuevo.setEstado("1");
        Listadisciplina obj=listadisciplinaService.save(nuevo);
        /*Listadisciplina obj = listadisciplinaService.save(nuevo);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(obj.getIdListadisciplina())
                        .toUri())
                .body(new SuccessResult<>(obj));
                */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdListadisciplina()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una listadisciplina")
    @PutMapping("/{id}")
    public ResponseEntity<ListadisciplinaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ListadisciplinaDTO dto) {
        dto.setIdListadisciplina(id);
        Listadisciplina obj = listadisciplinaService.update(id, mapperUtil.map(dto, Listadisciplina.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ListadisciplinaDTO.class));
    }

    @Operation(summary = "Elimina una listadisciplina")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Listadisciplina listadisciplina = listadisciplinaService.findById(id);
        if(listadisciplina==null){
            throw new EntityNotFoundException("Listadisciplina no encontrado");
        }
        listadisciplina.setEstado("0");
        listadisciplinaService.save(listadisciplina);
        return ResponseEntity.noContent().build();
    }
}
