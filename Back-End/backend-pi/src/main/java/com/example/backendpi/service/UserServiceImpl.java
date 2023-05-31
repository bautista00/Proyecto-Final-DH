package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import static com.example.backendpi.domain.Role.USER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.getFirstByEmail(userName);
        if (userDetails ==null){
            throw new UsernameNotFoundException(userName);
        }
        return userDetails;
    }

    @Override
    public UserDetails createUser(SignUpRequest signUpRequest) {
        try{
            return userRepository.save(User.builder()
                    .email(signUpRequest.getUsername())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .name(signUpRequest.getNombre())
                    .apellido(signUpRequest.getApellido())
                    .role(USER)
                    .build());
        }catch (DataIntegrityViolationException e){
            throw new ErrorResponseException(HttpStatus.CONFLICT,
                    ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                            "User already exist"),e);
        }
    }

    @Override
    public PageResponseDTO<UserDTO> getUsers(Pageable pageable) {

        Page<User> userPage = userRepository.findAll(pageable);
        return new PageResponseDTO<>(
                userPage.getContent().stream()
                        .map(user -> conversionService.convert(user, UserDTO.class)).toList()
                , userPage.getPageable()
                , userPage.getTotalPages());
    }


}
