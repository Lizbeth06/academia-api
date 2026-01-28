package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AnioDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.RecaptchaPedidoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import academiaapi.ipd.gob.pe.academiaapi.service.Impl.RecaptchaServiceImpl;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "recaptcha")
public class RecaptchaController {

    private final RecaptchaServiceImpl recaptchaService;

    @Operation(summary = "Validando key de recaptcha")
    @PostMapping("/recaptcha")
    public ResponseEntity<?> validandoRecaptcha(
            @RequestBody RecaptchaPedidoDTO request) {

        boolean isValid = recaptchaService.validarToken(
                request.getKey(),
                "registro"
        );

        if (!isValid) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("reCAPTCHA inválido o sospechoso");
        }

        return ResponseEntity.ok("Registro exitoso");
    }
}
