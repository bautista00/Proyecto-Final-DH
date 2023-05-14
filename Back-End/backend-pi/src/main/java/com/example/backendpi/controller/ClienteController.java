package com.example.backendpi.controller;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Cliente;
import com.example.backendpi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Cliente> buscarCliente(@PathVariable Long id){
        Optional<Cliente> optionalCliente= clienteService.buscarXId(id);
        if(optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> buscarTodos(){
        if(clienteService.buscarTodos().size()>0) {
            return ResponseEntity.ok(clienteService.buscarTodos());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
        clienteService.borrarXId(id);
        return ResponseEntity.ok("Se elimino el cliente con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.actualizar(cliente));
    }
}
