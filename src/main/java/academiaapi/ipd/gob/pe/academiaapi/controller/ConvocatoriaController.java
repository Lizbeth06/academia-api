package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ConvocatoriaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListaConvocatoriaDisciplinaSedeDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ListahorariobloqueDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.service.IImageService;
import academiaapi.ipd.gob.pe.academiaapi.service.IConvocatoriaService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/convocatoria")
@RequiredArgsConstructor
@Tag(name = "tbl_convocatoria")
public class ConvocatoriaController {
    private final IConvocatoriaService convocatoriaService;
    private final IImageService cloudinaryService;
    private final MapperUtil mapperUtil;

    private static final String FOLDER = "convocatorias";

    @Operation(summary = "Lista todas las convocatorias")
    @GetMapping
    public ResponseEntity<List<ConvocatoriaDTO>> findAll() {
        List<ConvocatoriaDTO> list = mapperUtil.mapList(convocatoriaService.findAll(), ConvocatoriaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una convocatoria")
    @GetMapping("/{id}")
    public ResponseEntity<ConvocatoriaDTO> findById(@PathVariable("id") Integer id) {
        Convocatoria obj = convocatoriaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ConvocatoriaDTO.class));
    }

    @Operation(summary = "Lista una convocatoria, horario y listahorario")
    @GetMapping("/{id}/listaconvocatoria")
    public ResponseEntity<ListahorariobloqueDTO> obtenerBloque(@PathVariable Integer id) {
        ListahorariobloqueDTO response = convocatoriaService.obtenerBloque(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lista total de las convocatorias")
    @GetMapping("/listaconvocatorias")
    public ResponseEntity<List<ListahorariobloqueDTO>> listaTotal() {

        List<ListahorariobloqueDTO> lista = convocatoriaService.listaTotal();

        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Lista por disciplina")
    @GetMapping("/listapordisciplina")
    public ResponseEntity<List<ListaConvocatoriaDisciplinaSedeDTO>> listaporDisciplina() {

        List<ListaConvocatoriaDisciplinaSedeDTO> lista = convocatoriaService.listaPorDisciplina();

        return ResponseEntity.ok(lista);
    }
}
