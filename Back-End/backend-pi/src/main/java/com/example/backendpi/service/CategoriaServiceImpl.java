package com.example.backendpi.service;

import com.example.backendpi.domain.Categoria;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.repository.CategoriaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;


    @Override
    public Categoria agregarCategoria(Categoria categoria) throws EntityExistsException {
        if (categoriaRepository.findByNombre(categoria.getNombre()) == null){
                return categoriaRepository.save(categoria);
        }
        throw new EntityExistsException("La categoria ya existe");
    }

    @Override
    public void eliminarCategoria(Long id) throws ResourceNotFoundException {
       Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            categoriaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("No se encontro la categoria con id "+ id);
        }

    }

    @Override
    public List<Categoria> listarCategorias() throws ResourceNotFoundException {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        if (categoriaList.size()>0){
            return categoriaList;
        }
        throw new ResourceNotFoundException("La listas de categoria esta vacia");
    }
}
