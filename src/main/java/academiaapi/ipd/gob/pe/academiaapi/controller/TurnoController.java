package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.common.result.DetailError;
import academiaapi.ipd.gob.pe.academiaapi.common.result.FailureResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.IResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.SuccessResult;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListadiaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.TurnoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Dias;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadia;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.service.IDiasService;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/turno")
@RequiredArgsConstructor
@Tag(name = "tbl_turno")
public class TurnoController {

    private final ITurnoService turnoService;
    private final IDiasService diasService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todos los turnos")
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> findAll() {
        List<TurnoDTO> list = mapperUtil.mapList(turnoService.findAll(), TurnoDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un turno")
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> findById(@PathVariable("id") Integer id) {
        Turno obj = turnoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TurnoDTO.class));
    }

    @Operation(summary = "Agrega un nuevo turno")
    @PostMapping
    public ResponseEntity<IResult> save(@Valid @RequestBody TurnoDTO dto) {
        Turno turno = mapperUtil.map(dto, Turno.class);

        List<Integer> diasIds = dto.getListadia().stream()
                .map(ld -> ld.getDias().getIdDias())
                .toList();

        String horaInicioStr = turno.getHorainicio().toString();
        String horaFinStr = turno.getHorafin().toString();

        List<Integer> duplicados = turnoService.getTurnoDuplicado(
                horaInicioStr,
                horaFinStr,
                diasIds,
                diasIds.size(),
                null
        );

        if (!duplicados.isEmpty()) {
            return ResponseEntity.ok(new FailureResult<>(
                    400,
                    new DetailError("06", "Ya existe un turno con la misma hora y los mismos días")
            ));
        }

        // Convertir ListadiaDTO a Listadia entidades
        List<Listadia> listadia = new ArrayList<>();
        for (ListadiaDTO ldDto : dto.getListadia()) {
            Dias dia = diasService.findById(ldDto.getDias().getIdDias());
            if (dia == null) {
                return ResponseEntity.ok(new FailureResult<>(
                        400,
                        new DetailError("06", "Día no encontrado")
                ));
            }

            Listadia ld = new Listadia();
            ld.setTurno(turno);
            ld.setDias(dia);
            ld.setEstado(ldDto.getEstado());

            listadia.add(ld);
        }
        turno.setListadia(listadia);

        Turno obj = turnoService.save(turno);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdTurno())
                .toUri();

        return  ResponseEntity.ok(new SuccessResult<>("Se guardó correctamente"));
    }

    @Operation(summary = "Actualiza un turno")
    @PutMapping("/{id}")
    public ResponseEntity<IResult> update(@Valid @PathVariable("id") Integer id, @RequestBody TurnoDTO dto) {
        Turno turno = mapperUtil.map(dto, Turno.class);
        turno.setIdTurno(id);

        List<Integer> diasIds = dto.getListadia().stream()
                .map(ld -> ld.getDias().getIdDias())
                .toList();

        String horaInicioStr = turno.getHorainicio().toString();
        String horaFinStr = turno.getHorafin().toString();

        List<Integer> duplicados = turnoService.getTurnoDuplicado(
                horaInicioStr,
                horaFinStr,
                diasIds,
                diasIds.size(),
                id
        );

        if (!duplicados.isEmpty()) {
            return ResponseEntity.ok(new FailureResult<>(
                    400,
                    new DetailError("06", "Ya existe un turno con la misma hora y los mismos días")
            ));
        }
        Turno existente = turnoService.findById(id);

        if (existente == null) {
            return ResponseEntity.ok(
                    new FailureResult<>(404, "Turno no encontrado"));
        }

        existente.setHorainicio(turno.getHorainicio());
        existente.setHorafin(turno.getHorafin());
        existente.setEstado(turno.getEstado());
        existente.setTipoturno(turno.getTipoturno());

        existente.getListadia().clear();
        List<Listadia> listadia = new ArrayList<>();
        for (ListadiaDTO ldDto : dto.getListadia()) {
            Dias dia = diasService.findById(ldDto.getDias().getIdDias());
            if (dia == null) {
                return ResponseEntity.ok(
                        new FailureResult<>(404, "Dias no encontrado"));
            }
            Listadia ld = new Listadia();
            ld.setTurno(existente);
            ld.setDias(dia);
            ld.setEstado(ldDto.getEstado());
            listadia.add(ld);
        }
        existente.setListadia(listadia);

        turnoService.save(existente);

        return ResponseEntity.ok(new SuccessResult<>("Actualización exitosa"));

    }

    @Operation(summary = "Elimina un turno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Turno turno = turnoService.findById(id);
        if (turno == null) {
            throw new EntityNotFoundException("Turno no encontrado");
        }
        turno.setEstado("0");
        turnoService.save(turno);
        return ResponseEntity.noContent().build();
    }
}
