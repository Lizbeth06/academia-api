package academiaapi.ipd.gob.pe.academiaapi.util;

import academiaapi.ipd.gob.pe.academiaapi.dto.AttachmentDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendMail(Mail mail, List<AttachmentDTO> attachments) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("email/email-template", context);
        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        for(AttachmentDTO a: attachments){
            helper.addAttachment(a.getFileName(), new ByteArrayResource(a.getContent()));
        }
        helper.setFrom(mail.getFrom());
//        helper.addAttachment("MyTestFile.txt", new ByteArrayResource("test".getBytes()));

        emailSender.send(message);
    }
}
