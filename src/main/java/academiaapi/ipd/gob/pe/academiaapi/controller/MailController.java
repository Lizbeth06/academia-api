package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AttachmentDTO;
import academiaapi.ipd.gob.pe.academiaapi.util.EmailUtil;
import academiaapi.ipd.gob.pe.academiaapi.util.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    private final EmailUtil emailUtil;
    @PostMapping("/sendmail")
    public ResponseEntity<Integer> sendMail() throws Exception{
        int rpta = 0;
        final int EXPIRATION_TIME =10;

        Mail mail = new Mail();
        mail.setFrom("jourdyhuamanchuchon@gmail.com");
        mail.setTo("jourdy.dev@gmail.com");
        mail.setSubject("INSCRIPCION ACADEMIA IPD EXITOSA");

        Map<String, Object> model = new HashMap<>();
        model.put("nombreAlumno","Pablito");
        model.put("disciplina","Judo");
        model.put("sede","Coliseo Romano");
        model.put("fechaInscripcion","18/12/2025");
        model.put("anio","2025");
        mail.setModel(model);

        AttachmentDTO attachment = new AttachmentDTO("txt", "text".getBytes());
        List<AttachmentDTO> attachments = new ArrayList<>();
        attachments.add(attachment);

        emailUtil.sendMail(mail, attachments);
        rpta=1;
        return ResponseEntity.ok(rpta);
    }
}
