package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.PersonaDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.UbigeoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import academiaapi.ipd.gob.pe.academiaapi.service.IPersonaService;
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
@RequestMapping("/api/persona")
@RequiredArgsConstructor
@Tag(name = "tbl_persona")
public class PersonaController {
    private final IPersonaService personaService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista toda la persona")
    @GetMapping
    public ResponseEntity<List<PersonaDTO>> findAll() {
        List<PersonaDTO> list=mapperUtil.mapList(personaService.findAll(), PersonaDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Lista una persona")
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findById(@PathVariable("id") Integer id) {
        Persona obj=personaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, PersonaDTO.class));
    }



    @Operation(summary = "Agrega una persona")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PersonaDTO dto) {
        Persona obj=personaService.save(mapperUtil.map(dto, Persona.class));
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPersona()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza una persona")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> update(@Valid  @PathVariable("id") Integer id, @RequestBody PersonaDTO dto) {
        dto.setIdPersona(id);
        Persona obj=personaService.update(id,mapperUtil.map(dto, Persona.class));
        return ResponseEntity.ok(mapperUtil.map(obj,PersonaDTO.class));
    }

    @Operation(summary = "Elimina una persona")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        personaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene persona por numero documento")
    @GetMapping("/searchxnumdoc")
    public ResponseEntity<List<PersonaDTO>> getxnumdoc(
            @RequestParam Integer tipodocumento,
            @RequestParam String numdocumento
    ) {
        List<PersonaDTO> persona = mapperUtil.mapList(personaService.getxmundoc(tipodocumento,numdocumento),PersonaDTO.class);
        return ResponseEntity.ok(persona);
    }

    @PutMapping("/updatecorreotelefono")
    public ResponseEntity<PersonaDTO> updateCorreoTelefono(
            @RequestParam Integer idPersona,
            @RequestParam String correo,
            @RequestParam String telefono
    ) {
        Persona personaActualizada = personaService.updateCorreoTelefono(idPersona, correo, telefono);
        return ResponseEntity.ok(mapperUtil.map(personaActualizada, PersonaDTO.class));
    }


}
