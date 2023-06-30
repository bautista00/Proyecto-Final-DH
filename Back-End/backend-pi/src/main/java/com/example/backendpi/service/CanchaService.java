package com.example.backendpi.service;

import com.example.backendpi.domain.Barrio;
import com.example.backendpi.domain.Cancha;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface CanchaService {


    public Cancha guardar(CanchaDTO canchaDTO,String token, List<MultipartFile> files) throws Exception;

    public Map<String, Object> buscarXId(Long id) throws ResourceNotFoundException;

    public void borrarXId(Long id)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarTodos();
    public void actualizar(CanchaDTO canchaDTO,Long id)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarXCategoria(String categoriaNombre)throws ResourceNotFoundException;

    public List<CanchaDTO> buscarPorUser(String token)throws ResourceNotFoundException;

   public List<CanchaDTO> buscarFiltrada(String barrio, String categoria) throws ResourceNotFoundException;

   public void eliminarCanchaDeFavoritos(Long id, String token) throws ResourceNotFoundException;
   public void agregarCanchaAFavoritos(Long id, String token) throws ResourceNotFoundException;
    boolean isCanchaFav(Long id, String token);


}
