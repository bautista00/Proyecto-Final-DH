package com.example.backendpi.service;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Cliente;
import com.example.backendpi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarXId(Long id){
        return clienteRepository.findById(id);
    }

    public void borrarXId(Long id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }
    public Cliente actualizar(Cliente cliente){
        if (clienteRepository.findById(cliente.getId()).isPresent()){
            clienteRepository.save(cliente);
        }
        return  cliente;
    }
}
