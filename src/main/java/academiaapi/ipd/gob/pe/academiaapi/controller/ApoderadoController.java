package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.common.result.*;
import academiaapi.ipd.gob.pe.academiaapi.dto.ApoderadoDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ApoderadoparticipanteDTO;
import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoService;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoparticipanteService;
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
@RequestMapping("/api/apoderado")
@RequiredArgsConstructor
@Tag(name="tbl_apoderado")
public class ApoderadoController {
    private final IApoderadoService apoderadoService;
    private final IApoderadoparticipanteService apoderadoparticipanteService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ApoderadoDTO>> findAll() {
        List<ApoderadoDTO> list = mapperUtil.mapList(apoderadoService.findAll(), ApoderadoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> findById(@PathVariable("id") Integer id) {
        Apoderado obj = apoderadoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ApoderadoDTO.class));
    }

    @Operation(summary = "Lista un apoderato por tipo de documento y numero de documento")
    @GetMapping("/documento")
    public ResponseEntity<IResult> findByDocumento(
        @RequestParam(required = true) Integer idTipodocumento,
        @RequestParam(required = true) String numDocumento
    ) {
        Optional<Apoderado> optionalApoderado =
                apoderadoService.findByIdTipoDocumentoAndNumDocumento(idTipodocumento, numDocumento);

        return optionalApoderado
                .<ResponseEntity<IResult>>map(apoderado -> {
                    ApoderadoDTO dto = mapperUtil.map(apoderado, ApoderadoDTO.class);
                    return ResponseEntity.ok(new SuccessResult<>(dto)); // HTTP 200
                })
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(new FailureResult<>(404, new DetailError("06", "No se encontro el apoderado"))));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ApoderadoDTO dto) {
        Apoderado obj = apoderadoService.save(mapperUtil.map(dto, Apoderado.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdApoderado()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ApoderadoDTO dto) {
        dto.setIdApoderado(id);
        Apoderado obj = apoderadoService.update(id, mapperUtil.map(dto, Apoderado.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ApoderadoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        apoderadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
