package com.example.backendpi.service;

import com.example.backendpi.config.MailProperties;
import com.example.backendpi.domain.User;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.MailSenderException;
import com.example.backendpi.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
            helper.setText(body, true);
            helper.setSubject(subject);
            helper.setFrom(mailProperties.username());
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new MailSenderException(e);
        }
    }




    public void sendVerificationEmail(SignUpRequest signUpRequest) {
        User user = userRepository.findByEmail(signUpRequest.getUsername());
        String subject = "Email Verification";
        String fullName = user.getName() + " " + user.getApellido();
        String content = "<html>" +
                "<body>" +
                "<div style='text-align: start;'>" +
                "<img src='https://bucket-fieldrent-img.s3.us-east-2.amazonaws.com/logoEmail.png' alt='Logo' style='width: 300px;'>" + // Reemplaza con la ruta de tu logo y ajusta el ancho según sea necesario
                "<h1 style='color: #333;'>Bienvenido " + fullName + "</h1>" +
                "<p style='color: #777;'>Por favor, haz clic en el siguiente enlace para verificar tu cuenta:</p>" +
                "<a href='http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/verify" + "' style='display: inline-block; background-color: #007bff; color: #fff; text-decoration: none; padding: 10px 20px; border-radius: 5px;'>Verificar cuenta</a>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendMail(signUpRequest.getUsername(), subject, content);
    }
    public void sendCongratsEmail(User user) {
        String subject = "Verificación completa";
        String fullName = user.getName() + " " + user.getApellido();

        String content = "<html>" +
                "<body>" +
                "<div style='text-align: start;'>" +
                "<img src='https://bucket-fieldrent-img.s3.us-east-2.amazonaws.com/logoEmail.png' alt='Logo' style='width: 300px;'>" + // Reemplaza con la ruta de tu logo y ajusta el ancho según sea necesario
                "<h1 style='color: #333;'>Verificación completa</h1>" +
                "<p style='color: #777;'>Felicitaciones, " + fullName +" tu cuenta ya ha sido verificada. ¡Ahora puedes navegar libremente en nuestra página!</p>" +
                "<a href='http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/login' style='display: inline-block; background-color: #007bff; color: #fff; text-decoration: none; padding: 10px 20px; border-radius: 5px;'>Iniciar sesión</a>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendMail(user.getEmail(), subject, content);
    }






}

