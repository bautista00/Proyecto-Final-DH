package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.AuthenticationResponse;
import com.example.backendpi.dto.LoginRequest;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final EmailService emailService;

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) throws ResourceNotFoundException {
        if(userRepository.findByEmail(loginRequest.getUsername()).getVerified()) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            String jwt = jwtService
                    .generateToken((UserDetails) authentication.getPrincipal());

            return AuthenticationResponse.builder()
                    .jwt(jwt)
                    .rol(userRepository.findByEmail(loginRequest.getUsername()).getRole().name())
                    .build();
        }
        throw new ResourceNotFoundException("El usuario no a verificado aun");

        }

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.createUser(signUpRequest);
        emailService.sendVerificationEmail(signUpRequest);
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(userDetails))
                .email(signUpRequest.getUsername())
                .build();
    }

    public boolean verifyUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setVerified(true);
            user.setTokenEmail(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

   // emailService.sendMail(signUpRequest.getUsername(),"Gracias por registarse en Field Rent",
   //         "Bienvenido, "+signUpRequest.getNombre()+" " +signUpRequest.getApellido()+" gracias por confiar en nostros. "
   //         +" Visita nuestro sitio web: <a href=\"" + "http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/Login" + "\">"+ "http://bucket-fieldrent-front.s3-website.us-east-2.amazonaws.com/Login" + "</a>"
   //         );
}
