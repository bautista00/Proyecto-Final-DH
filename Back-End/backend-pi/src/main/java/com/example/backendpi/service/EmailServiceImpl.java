package com.example.backendpi.service;

import com.example.backendpi.config.MailProperties;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.MailSenderException;
import com.example.backendpi.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
@EnableAsync
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;
    private final UserRepository userRepository;

    @Override
    @Async
    public void sendMail(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setText(body , true);
            helper.setSubject(subject);
            helper.setFrom(mailProperties.username());
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new MailSenderException(e);
        }
    }


    public void sendVerificationEmail(SignUpRequest signUpRequest) {
        User user= userRepository.findByEmail(signUpRequest.getUsername());
        String verification = user.getTokenEmail();
        String subject = "Email Verification";
        String body = "Porfavor hace click en el link de abajo para poder verificar tu cuenta:\n" + "http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/verify/"+verification;
        sendMail(signUpRequest.getUsername(), subject, body);
    }

    public void sendCongratsEmail(User user){
        String subject = "Verifiacion completa";
        String body = "Felicitaciones tu cuenta ya fue verificada, ya podes navegar libremente en nuestra pagina.\n" +
        "\" Con este link podras ingresar a nustra pagina http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/login";
        sendMail(user.getEmail(),subject,body);
    }

}

