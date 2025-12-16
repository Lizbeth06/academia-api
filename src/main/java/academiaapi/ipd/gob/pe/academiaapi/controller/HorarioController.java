package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.HorarioDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.HorarioPorDisciplinaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.InscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.*;
import academiaapi.ipd.gob.pe.academiaapi.repository.IDisciplinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListadisciplinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.ISedeRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITurnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IColaGenericoService;
import academiaapi.ipd.gob.pe.academiaapi.service.IDisciplinaService;
import academiaapi.ipd.gob.pe.academiaapi.service.IHorarioService;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
import academiaapi.ipd.gob.pe.academiaapi.service.Impl.ColaGenericoServiceImpl;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/horario")
@RequiredArgsConstructor
@Tag(name = "tbl_horario")
public class HorarioController {

    private final IColaGenericoService colaGenericoService;
    private final IHorarioService horarioService;
    private final ISedeRepository sedeRepository;
    private final IDisciplinaRepository disciplinaRepository;
    private final IListadisciplinaRepository listadisciplinaRepository;
    private final ITurnoRepository turnoRepository;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista todo los horarios")
    @GetMapping
    public ResponseEntity<List<HorarioDTO>> findAll() {
        List<HorarioDTO> list = mapperUtil.mapList(horarioService.findAll(), HorarioDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista un horario")
    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> findById(@PathVariable("id") Integer id) {
        Horario obj = horarioService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, HorarioDTO.class));
    }

    @Operation(summary = "Lista horarios por sede y agrupa por disciplina")
    @GetMapping("/listagrupada/{idSede}")
    public ResponseEntity<List<HorarioPorDisciplinaDTO>> findAllHorarioagrupado(@PathVariable Integer idSede) {
        List<Horario> listHorarios = horarioService.getHorarioxsede(idSede);
        Map<Disciplina, List<Horario>> grouped = listHorarios.stream()
                .collect(Collectors.groupingBy(h -> h.getListadisciplina().getDisciplina()));

        List<HorarioPorDisciplinaDTO> list = grouped.entrySet().stream()
                .map(entry -> {
                    HorarioPorDisciplinaDTO dto = new HorarioPorDisciplinaDTO();
                    Disciplina disciplina = entry.getKey();

                    dto.setIdDisciplina(disciplina.getIdDisciplina());
                    dto.setNombreDisciplina(disciplina.getDescripcion());

                    List<HorarioDTO> horariosDTO = entry.getValue().stream()
                            .map(h -> mapperUtil.map(h, HorarioDTO.class))
                            .toList();

                    dto.setHorarios(horariosDTO);
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Guarda un horario")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody HorarioDTO dto) {
        Horario obj = horarioService.save(mapperUtil.map(dto, Horario.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdHorario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Agrega una lista de horarios")
    @PostMapping("/guardar/lote")
    @Transactional
    public ResponseEntity<Void> saveMany(@Valid @RequestBody List<HorarioDTO> dtos) {

       /* // ejecutar lote en la cola genérica
        colaGenericoService.ejecutarLote(
                this::saveSingle,  // referencia al método que guarda un horario individual
                obj,          // lista de horarios
                200                // delay entre tareas en ms (opcional)
        );

        // 202 Accepted: la request fue aceptada y se procesará en segundo plano
        return ResponseEntity.accepted().build();*/
        if (dtos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        HorarioDTO primerDto = dtos.get(0);

        Sede sede = sedeRepository.findById(primerDto.getListadisciplina().getSede().getIdSede())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        Disciplina disciplina = disciplinaRepository.findById(primerDto.getListadisciplina().getDisciplina().getIdDisciplina())
                .orElseThrow(() -> new RuntimeException("Disciplina no encontrada"));

        Listadisciplina listadisciplina = listadisciplinaRepository.findBySedeAndDisciplinaAndEstadoIn(sede, disciplina, List.of("1", "0")).orElseGet(() -> {
            Listadisciplina nueva = new Listadisciplina();
            nueva.setSede(sede);
            nueva.setDisciplina(disciplina);
            nueva.setEstado("1");
            return listadisciplinaRepository.save(nueva);
        });
        listadisciplina.setEstado("1");
        listadisciplinaRepository.save(listadisciplina);
        for (HorarioDTO dto : dtos) {
            Horario horarioNuevo = mapperUtil.map(dto, Horario.class);

            Turno turno = turnoRepository.findById(dto.getTurno().getIdTurno())
                    .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
            horarioNuevo.setTurno(turno);

            horarioNuevo.setListadisciplina(listadisciplina);
            horarioNuevo.setContador(0);
            horarioNuevo.setEstado("1");
            horarioNuevo.setFechacreada(LocalDateTime.now());
            horarioNuevo.setUsuariocrea("1");
            horarioService.save(horarioNuevo);

        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Actualiza un horario")
    @PutMapping("/{id}")
    public ResponseEntity<HorarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody HorarioDTO dto) {
        Horario actual = horarioService.findById(id);

        // 2. Mapear lo que manda el usuario
        Horario horario = mapperUtil.map(dto, Horario.class);
        horario.setIdHorario(id);

        // CAMPOS QUE SE MANTIENEN
        horario.setContador(actual.getContador());
        horario.setUsuariocrea(actual.getUsuariocrea());
        horario.setFechacreada(actual.getFechacreada());

        Horario actualizado = horarioService.update(id, horario);

        return ResponseEntity.ok(mapperUtil.map(actualizado, HorarioDTO.class));
    }

    @Operation(summary = "Elimina un horario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

        Horario horario = horarioService.findById(id);
        if (horario == null) {
            throw new EntityNotFoundException("Horario no encontrado");
        }
        horario.setEstado("0");
        horario.setUsuariomodifica("1");
        horario.setFechamodificada(LocalDateTime.now());
        horarioService.save(horario);
        return ResponseEntity.noContent().build();
    }
}
