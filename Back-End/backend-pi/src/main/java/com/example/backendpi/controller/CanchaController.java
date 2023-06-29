package com.example.backendpi.controller;


import com.example.backendpi.domain.Barrio;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.service.CanchaService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping
@AllArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    private final ObjectMapper objectMapper;


//    @PostMapping("/owner/addcancha")
//    public ResponseEntity<Cancha> agregarCancha(@RequestParam(value="canchaDTO") String canchaDTO, @RequestParam(value = "token") String token,@RequestPart(value="file") List<MultipartFile> files) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        CanchaDTO cancha = objectMapper.readValue(canchaDTO, CanchaDTO.class);
//        return ResponseEntity.ok(canchaService.guardar(cancha,token, files));
//    }

    @PostMapping("/owner/addcancha")
    public ResponseEntity<Cancha> agregarCancha(@RequestParam(value="canchaDTO") String canchaDTO, @RequestParam(value = "token") String token,@RequestPart(value="file") List<MultipartFile> files) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CanchaDTO cancha = objectMapper.readValue(canchaDTO, CanchaDTO.class);
        return ResponseEntity.ok(canchaService.guardar(cancha,token, files));
    }

    @GetMapping("/detailcancha/{id}")
    public ResponseEntity<Map<String, Object>> buscarCancha(@PathVariable("id") Long id) throws ResourceNotFoundException {
       return ResponseEntity.ok(canchaService.buscarXId(id));
    }

    @GetMapping("/listallcanchas")
    public ResponseEntity<List<CanchaDTO>> buscarTodos (){
        return ResponseEntity.ok(canchaService.buscarTodos());
    }

    @GetMapping("/listxsportcanchas")
    public ResponseEntity<List<CanchaDTO>> buscarXDeporte (@RequestParam (value="categoriaNombre") String categoriaNombre)throws ResourceNotFoundException{
        return ResponseEntity.ok(canchaService.buscarXCategoria(categoriaNombre));
    }


    @GetMapping("/owner/listxownercanchas")
    public ResponseEntity<List<CanchaDTO>> buscarXOwner (@RequestParam(value = "token") String token) throws ResourceNotFoundException {
        return ResponseEntity.ok(canchaService.buscarPorUser(token));
    }


    @DeleteMapping("/owner/deletecancha/{id}")
    public ResponseEntity<String> borrarCancha (@PathVariable Long id) throws ResourceNotFoundException {
        canchaService.borrarXId(id);
       return ResponseEntity.ok("La cancha con id " +id+ " se ha eliminado correctamente");
    }


    @PutMapping("/owner/updatecancha")
    public ResponseEntity<CanchaDTO> actualizarCancha(@RequestBody CanchaDTO canchaDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(canchaService.actualizar(canchaDTO));
     }


     @GetMapping("/buscarFiltradas")
    public ResponseEntity<List<CanchaDTO>> buscarPorFiltro(@RequestParam(value = "barrio") String barrio, @RequestParam(value = "categoria")  String categoria)throws ResourceNotFoundException{
        return ResponseEntity.ok(canchaService.buscarFiltrada(barrio, categoria));
     }


//     @PutMapping("/user/agregarAFav")
//    public ResponseEntity<String> agregarAFav(@RequestParam(value = "canchaDTO") CanchaDTO canchaDTO, @RequestParam(value = "token") String token) throws ResourceNotFoundException{
//        canchaService.agregarAFavoritos(canchaDTO,token);
//        return ResponseEntity.ok("Se agrego correctamente");
//     }
//
//    @PutMapping("/user/eliminarDeFav")
//    public ResponseEntity<String> eliminarDeFav(@RequestParam(value = "canchaDTO") CanchaDTO canchaDTO, @RequestParam(value = "token") String token) throws ResourceNotFoundException{
//        canchaService.eliminarDeFavoritos(canchaDTO,token);
//        return ResponseEntity.ok("Se elimino correctamente");
//    }


}

