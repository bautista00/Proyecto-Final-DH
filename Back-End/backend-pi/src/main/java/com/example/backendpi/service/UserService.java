package com.example.backendpi.service;

import com.example.backendpi.domain.User;
import com.example.backendpi.dto.PageResponseDTO;
import com.example.backendpi.dto.SignUpRequest;
import com.example.backendpi.dto.UserDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.ResourceAccessException;

public interface UserService extends UserDetailsService {

    UserDetails createUser(SignUpRequest signUpRequest);

    PageResponseDTO<UserDTO> getUsers(Pageable pageable);

    User getUser(String token)throws ResourceNotFoundException;




}
