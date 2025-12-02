package academiaapi.ipd.gob.pe.academiaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/externo")
public class ApiExternos {
    private String urlPeticion="http://172.16.10.112:81/ServiciosIPD/web";

    private final RestTemplate restTemplate;

    public ApiExternos(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CrossOrigin(origins = "*")  // habilitar los CORS para Angular
    @GetMapping("/dnireniec/{dni}")
    public ResponseEntity<?> getDatosReniec(@PathVariable String dni) {
        String url = UriComponentsBuilder.fromHttpUrl(urlPeticion).pathSegment("reniec", "nrodoc", dni)
                .toUriString();

        try {
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error consultando DNI");
            error.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/sededeportivo/{codigo}")
    public ResponseEntity<?> getSededepotivo(@PathVariable String codigo) {

        String url = UriComponentsBuilder.fromHttpUrl(urlPeticion).pathSegment("ipd", "edificaciondeportiva", codigo)
                .toUriString();

        try {
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error consultando edificación");
            error.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping({"/departamento"})
    public ResponseEntity<?> getDepartamento() {

        String url = UriComponentsBuilder.fromHttpUrl(urlPeticion).pathSegment("ipd", "ubigeo")
                .toUriString();

        try {
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error consultando departamento");
            error.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/provincia/{ubiDep}")
    public ResponseEntity<?> getProvincia(@PathVariable String ubiDep) {
        String url = UriComponentsBuilder.fromHttpUrl(urlPeticion).pathSegment("ipd", "ubigeo", ubiDep)
                .toUriString();

        try {
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
         } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error consultando provincia");
            error.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/distrito/{ubiDep}/{ubiProv}")
    public ResponseEntity<?> getDistrito(@PathVariable String ubiDep,@PathVariable String ubiProv) {

        String url = UriComponentsBuilder.fromHttpUrl(urlPeticion).pathSegment("ipd", "ubigeo", ubiDep, ubiProv)
                .toUriString();

        try {
            Object response = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error consultando distrito");
            error.put("detalle", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
