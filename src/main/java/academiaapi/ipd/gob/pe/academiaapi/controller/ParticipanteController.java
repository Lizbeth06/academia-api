package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.common.result.DetailError;
import academiaapi.ipd.gob.pe.academiaapi.common.result.FailureResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.IResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.SuccessResult;
import academiaapi.ipd.gob.pe.academiaapi.dto.ApoderadoDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ParticipanteDTO;
import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
import academiaapi.ipd.gob.pe.academiaapi.service.IParticipanteService;
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
import java.util.Optional;

@Controller
@RequestMapping("/api/participante")
@RequiredArgsConstructor
@Tag(name = "tbl_participante")
public class ParticipanteController {
    private final IParticipanteService participanteService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ParticipanteDTO>> findAll() {
        List<ParticipanteDTO> list = mapperUtil.mapList(participanteService.findAll(), ParticipanteDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> findById(@PathVariable("id") Integer id) {
        Participante obj = participanteService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ParticipanteDTO.class));
    }

    @Operation(summary = "Lista un participante por tipo de documento y numero de documento")
    @GetMapping("/documento")
    public ResponseEntity<IResult> findByIdDocumento(
            @RequestParam(required = true) Integer idTipodocumento,
            @RequestParam(required = true) String numDocumento
    ) {

        Optional<Participante> optionalParticipante = participanteService.findByIdTipoDocumentoAndNumDocumento(idTipodocumento, numDocumento);

        return optionalParticipante
                .<ResponseEntity<IResult>>map(participante -> {
                    ParticipanteDTO dto = mapperUtil.map(participante, ParticipanteDTO.class);
                    return ResponseEntity.ok(new SuccessResult<>(dto)); // HTTP 200
                })
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(new FailureResult<>(404, new DetailError("06", "No se encontro a la persona"))));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ParticipanteDTO dto) {
        Participante obj = participanteService.save(mapperUtil.map(dto, Participante.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdParticipante()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ParticipanteDTO dto) {
        dto.setIdParticipante(id);
        Participante obj = participanteService.update(id, mapperUtil.map(dto, Participante.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ParticipanteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        participanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
