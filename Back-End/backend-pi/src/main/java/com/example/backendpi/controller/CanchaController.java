package com.example.backendpi.controller;

import com.example.backendpi.domain.Cancha;
import com.example.backendpi.service.CanchaService;
import com.example.backendpi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/canchas")
public class CanchaController {
    private CanchaService canchaService;
    private UserServiceImpl usuarioServiceImpl;


    @Autowired
    public CanchaController(CanchaService canchaService, UserServiceImpl usuarioServiceImpl) {
        this.canchaService = canchaService;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

//    @PostMapping
//    public ResponseEntity<Cancha> agregarCancha(@RequestBody Cancha cancha){
//        if(usuarioServiceImpl.buscarUsuario(cancha.getUser().getId()).isPresent()){
//            return ResponseEntity.ok(canchaService.guardar(cancha));
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity <Cancha> buscarCancha(@PathVariable Long id){
        return null;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cancha>> buscarTodos(){
      return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCancha(@PathVariable Long id){
        canchaService.borrarXId(id);
        return ResponseEntity.ok("Se elimino la cancha con el id: "+id);
    }

    @PutMapping
    public ResponseEntity<Cancha> actualizarCancha(@RequestBody Cancha cancha){
        return null;
    }

}
