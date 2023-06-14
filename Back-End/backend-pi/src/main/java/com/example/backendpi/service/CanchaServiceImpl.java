package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.CanchaDTOaCanchaConverter;
import com.example.backendpi.converters.CanchaToCanchaDTOConverter;
import com.example.backendpi.domain.Cancha;
import com.example.backendpi.domain.Categoria;
import com.example.backendpi.domain.Images;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CanchaServiceImpl implements CanchaService{

    private final CanchaRepository canchaRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CanchaDTOaCanchaConverter canchaDTOaCanchaConverter;
    private final CanchaToCanchaDTOConverter canchaToCanchaDTOConverter;
    private final AwsS3Service awsS3Service ;
    private final ImagesRepository imagesRepository;
    private final DomicilioService domicilioService;
    private final CategoriaRepository categoriaRepository;
    private final BarrioRepository barrioRepository;
    @Override
    public Cancha guardar(CanchaDTO canchaDTO,String token, MultipartFile file) throws Exception {
        Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
        cancha.setUser(userRepository.findByEmail(jwtService.extractUserName(token)));
        cancha.setTurnoList(new ArrayList<>());
        Images images = new Images();
        images.setCancha(cancha);
        images.setUrl(awsS3Service.generateImageUrl(awsS3Service.uploadFile(file)));
        Categoria categoria = categoriaRepository.findByNombre(canchaDTO.getCategoria().getNombre());
        if(categoria!= null){
            cancha.setCategoria(categoria);
        }
        domicilioService.guardar(cancha.getDomicilio());
        canchaRepository.save(cancha);
        imagesRepository.save(images);
        return cancha;
    }

    @Override
    public Optional<CanchaDTO> buscarXId(Long id) throws ResourceNotFoundException{
        Optional<Cancha> cancha  = canchaRepository.findById(id);
        if(cancha.isPresent()){
            return Optional.of(canchaToCanchaDTOConverter.convert(cancha.get()));
        }else {
            throw new ResourceNotFoundException("No existe la cancha buscada con ese id" + id);
        }
    }

    @Override
    public void borrarXId(Long id) throws ResourceNotFoundException{
        if(canchaRepository.findById(id).isPresent()){
            canchaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("No se pudo borrar la cancha con ese id" + id);
        }
    }

    @Override
    public List<CanchaDTO> buscarTodos() throws NotFoundException{
      if (canchaRepository.findAll().size()>0){
          List<CanchaDTO> canchaDTOS = new ArrayList<>();
          List<Cancha> canchasList = canchaRepository.findAll();
          if (canchasList.size()>0){
              for (Cancha cancha: canchasList){
                  canchaDTOS.add(canchaToCanchaDTOConverter.convert(cancha));
              }
          }
          return canchaDTOS;
      }else {
          throw new NotFoundException("No se encontro una lista de canchas");
      }
    }

    @Override
    public CanchaDTO actualizar(CanchaDTO canchaDTO) throws ResourceNotFoundException{
        if(canchaRepository.findById(canchaDTO.getId()).isPresent()){
            canchaToCanchaDTOConverter.convert(canchaRepository.save(canchaDTOaCanchaConverter.convert(canchaDTO)));
            return canchaDTO;
        }
        else {
            throw new ResourceNotFoundException("No se pudo actualizar correctamente la informacion");
        }
    }

    @Override
    public List<CanchaDTO> buscarXCategoria(Categoria categoria)throws ResourceNotFoundException {
        if(canchaRepository.findByCategoria(categoria).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByCategoria(categoria);
            return canchaDTOS;
        }
        else {
            throw new ResourceNotFoundException("No existe la categoria buscadad");
        }
    }

    @Override
    public List<CanchaDTO> buscarPorUser(String token) throws ResourceNotFoundException {
        if(canchaRepository.findByUserEmail(jwtService.extractUserName(token)).size()>0) {
            List<CanchaDTO> canchaDTOS = canchaRepository.findByUserEmail(jwtService.extractUserName(token));
            return canchaDTOS;
        }else {
            throw new ResourceNotFoundException("No existen las canchas buscadas por el propietario");
        }
    }

}
