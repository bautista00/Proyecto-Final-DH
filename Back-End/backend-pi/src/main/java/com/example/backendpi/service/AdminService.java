package com.example.backendpi.service;

import com.example.backendpi.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

}

// QUEDA PARA REVISION, CONSULTAR CON RODO//