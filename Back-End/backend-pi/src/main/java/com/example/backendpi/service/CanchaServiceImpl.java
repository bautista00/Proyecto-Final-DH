package com.example.backendpi.service;

import com.amazonaws.services.mq.model.NotFoundException;
import com.example.backendpi.converters.*;
import com.example.backendpi.domain.*;
import com.example.backendpi.dto.CanchaDTO;
import com.example.backendpi.dto.TurnoDTO;
import com.example.backendpi.exceptions.ResourceNotFoundException;
import com.example.backendpi.jwt.JwtService;
import com.example.backendpi.repository.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CanchaServiceImpl implements CanchaService{

    private final CanchaRepository canchaRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CanchaDTOaCanchaConverter canchaDTOaCanchaConverter;
    private final CanchaToCanchaDTOConverter canchaToCanchaDTOConverter;

    private final ImagesToImagesDTOConverter imagesToImagesDTOConverter;
    private final ImagesDTOToImagesConverter imagesDTOToImagesConverter;
    private final ValoracionDTOToValoracionConverter valoracionDTOToValoracionConverter;
    private final ValoracionToValoracionDTOConverter valoracionToValoracionDTOConverter;
    private final AwsS3Service awsS3Service ;
    private final ImagesRepository imagesRepository;
    private final DomicilioService domicilioService;
    private final CategoriaRepository categoriaRepository;
    private final TurnoDTOToTurnoConverter turnoDTOToTurnoConverter;
    private final ServicioRepository servicioRepository;

    private final TurnoRepository turnoRepository;

    private final CriterioRepository criterioRepository;

    private final DomicilioRepository domicilioRepository;


//@Override
//public Cancha guardar(CanchaDTO canchaDTO, String token, List<MultipartFile> files) throws Exception {
//    Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
//    cancha.setUser(userRepository.findByEmail(jwtService.extractUserName(token)));
//    cancha.setTurnoList(new ArrayList<>());
//    cancha.setServicioList(new ArrayList<>());
//    Images images = new Images();
//    images.setCancha(cancha);
//    images.setUrl(awsS3Service.generateImageUrls(awsS3Service.uploadFiles(files)));
//    Categoria categoria = categoriaRepository.findByNombre(canchaDTO.getCategoria().getNombre());
//    if (categoria != null) {
//        cancha.setCategoria(categoria);
//    }
//    List<Servicio> servicioList = new ArrayList<>();
//    for (Servicio servicio : canchaDTO.getServicioList()) {
//        Servicio servicioExistente = servicioRepository.findByNombre(servicio.getNombre());
//        if (servicioExistente != null) {
//            servicioList.add(servicioExistente);
//        }
//    }
//    cancha.setServicioList(servicioList);
//    List<Criterios> criteriosList = canchaDTO.getCriteriosList();
//    if (!criteriosList.isEmpty()) {
//        cancha.setCriteriosList(criteriosList);
//    }
//
//    domicilioService.guardar(cancha.getDomicilio());
//    canchaRepository.save(cancha);
//    imagesRepository.save(images);
//    return cancha;
//}
//@Override
//public Cancha guardar(CanchaDTO canchaDTO, String token, List<MultipartFile> files) throws Exception {
//    Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
//    cancha.setUser(userRepository.findByEmail(jwtService.extractUserName(token)));
//    cancha.setTurnoList(new ArrayList<>());
//    cancha.setServicioList(new ArrayList<>());
//    cancha.setCriteriosList(new ArrayList<>());
//
//    // Subir las imágenes a AWS S3 y generar las URL correspondientes
//    List<String> imageUrls = awsS3Service.generateImageUrls(awsS3Service.uploadFiles(files));
//
//    Images images = new Images();
//    images.setUrl(imageUrls);
//
//    Categoria categoria = categoriaRepository.findByNombre(canchaDTO.getCategoria().getNombre());
//    if (categoria != null) {
//        cancha.setCategoria(categoria);
//    }
//
//    List<Servicio> servicioList = new ArrayList<>();
//    for (Servicio servicio : canchaDTO.getServicioList()) {
//        Servicio servicioExistente = servicioRepository.findByNombre(servicio.getNombre());
//        if (servicioExistente != null) {
//            servicioList.add(servicioExistente);
//        }
//    }
//    cancha.setServicioList(servicioList);
//
//    List<Criterios> criteriosList = canchaDTO.getCriteriosList();
//    if (!criteriosList.isEmpty()) {
//        for (Criterios criterio : criteriosList) {
//            criterio.setCancha(cancha); // Asignar la instancia de Cancha a cada Criterios
//        }
//        cancha.setCriteriosList(criteriosList);
//    }
//
//    Domicilio domicilio = cancha.getDomicilio();
//    domicilioService.guardar(domicilio);
//    cancha.setDomicilio(domicilio);
//
//    // Guardar primero la instancia de Cancha
//    canchaRepository.save(cancha);
//
//    images.setCancha(cancha); // Asignar la instancia de Cancha guardada a Images
//    imagesRepository.save(images);
//
//    cancha.setImages(images); // Establecer la relación bidireccional entre Cancha e Images
//
//    return cancha;
//}
@Override
public Cancha guardar(CanchaDTO canchaDTO, String token, List<MultipartFile> files) throws Exception {
    Cancha cancha = canchaDTOaCanchaConverter.convert(canchaDTO);
    cancha.setUser(userRepository.findByEmail(jwtService.extractUserName(token)));
    cancha.setTurnoList(new ArrayList<>());
    cancha.setServicioList(new ArrayList<>());
    cancha.setCriteriosList(new ArrayList<>());

    // Subir las imágenes a AWS S3 y generar las URL correspondientes
    List<String> imageUrls = awsS3Service.generateImageUrls(awsS3Service.uploadFiles(files));

    // Crear y guardar el objeto Images
    Images images = new Images();
    images.setUrl(imageUrls);
    imagesRepository.save(images);

    // Establecer la relación entre Cancha e Images
    cancha.setImages(images);

    Categoria categoria = categoriaRepository.findByNombre(canchaDTO.getCategoria().getNombre());
    if (categoria != null) {
        cancha.setCategoria(categoria);
    }

    List<Servicio> servicioList = new ArrayList<>();
    for (Servicio servicio : canchaDTO.getServicioList()) {
        Servicio servicioExistente = servicioRepository.findByNombre(servicio.getNombre());
        if (servicioExistente != null) {
            servicioList.add(servicioExistente);
        }
    }
    cancha.setServicioList(servicioList);

    List<Criterios> criteriosList = canchaDTO.getCriteriosList();
    if (!criteriosList.isEmpty()) {
        for (Criterios criterio : criteriosList) {
            criterio.setCancha(cancha); // Asignar la instancia de Cancha a cada Criterios
        }
        cancha.setCriteriosList(criteriosList);
    }

    Domicilio domicilio = cancha.getDomicilio();
    domicilioService.guardar(domicilio);
    cancha.setDomicilio(domicilio);

    // Guardar la instancia de Cancha
    return canchaRepository.save(cancha);
}












    @Override
    public Map<String, Object> buscarXId(Long id) throws ResourceNotFoundException {
        Optional<Cancha> cancha = canchaRepository.findById(id);
        List<Turno> turnoList = turnoRepository.findByCanchaWithFecha(id);
        List<Turno> turnoListVencido = turnoRepository.findByCanchaWithFechaVencido(id);

        if (cancha.isPresent()) {
            CanchaDTO canchaDTO = canchaToCanchaDTOConverter.convert(cancha.get());
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("canchaDTO", canchaDTO);
            resultado.put("turnoList", turnoList);

            for (Turno turno : turnoListVencido) {
                turno.setCompletado(true);
            }

            return resultado;
        } else {
            throw new ResourceNotFoundException("No existe la cancha buscada con ese id" + id);
        }
    }


    @Override
    public void borrarXId(Long id) throws ResourceNotFoundException {
        Optional<Cancha> canchaOptional = canchaRepository.findById(id);
        if (canchaOptional.isPresent()) {
            Cancha cancha = canchaOptional.get();
            canchaRepository.borrarCancha(cancha.getId());
            criterioRepository.borrarPorCanchaID(cancha.getId());
            canchaRepository.delete(cancha);
        } else {
            throw new ResourceNotFoundException("No se pudo borrar la cancha con ese id: " + id);
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
    public void actualizar(CanchaDTO canchaDTO, Long id) throws ResourceNotFoundException {
        if (canchaRepository.findById(id).isPresent()) {
            Cancha canchaExistente = canchaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró la cancha con ID: " + canchaDTO.getId()));

            if (canchaDTO.getCategoria() != null) {
                canchaExistente.setCategoria(canchaDTO.getCategoria());
            }
            if (canchaDTO.getDomicilio() != null) {
                canchaExistente.setDomicilio(canchaDTO.getDomicilio());
            }
            if (canchaDTO.getPrecio() != null) {
                canchaExistente.setPrecioxhora(canchaDTO.getPrecio());
            }
            if (canchaDTO.getTelefono() != null) {
                canchaExistente.setTelefono(canchaDTO.getTelefono());
            }
            if (canchaDTO.getNombre() != null) {
                canchaExistente.setNombre(canchaDTO.getNombre());
            }
            if (canchaDTO.getHoraApertura() != null) {
                canchaExistente.setHoraApertura(canchaDTO.getHoraApertura());
            }
            if (canchaDTO.getHoraCierre() != null) {
                canchaExistente.setHoraCierre(canchaDTO.getHoraCierre());
            }
            if (canchaDTO.getCriteriosList() != null) {
                canchaExistente.setCriteriosList(canchaDTO.getCriteriosList());
            }
            if (canchaDTO.getImages() != null) {
                canchaExistente.setImages(canchaDTO.getImages());
            }
            if (canchaDTO.getValoracionList() != null) {
                List<Valoracion> valoraciones = canchaDTO.getValoracionList().stream()
                        .map(valoracionDTOToValoracionConverter::convert)
                        .collect(Collectors.toList());
                canchaExistente.setValoracionList(valoraciones);
            }
            if (canchaDTO.getPromedio() != null) {
                canchaExistente.setPromedioPuntuacion(canchaDTO.getPromedio());
            }
            if (canchaDTO.getServicioList() != null) {
                canchaExistente.setServicioList(canchaDTO.getServicioList());
            }
            if (canchaDTO.getDescripcion() != null) {
                canchaExistente.setDescripcion(canchaDTO.getDescripcion());
            }

            canchaRepository.save(canchaExistente);
        } else {
            throw new ResourceNotFoundException("No se pudo actualizar correctamente la información de la cancha");
        }
    }


//    @Override
//    public CanchaDTO actualizar(CanchaDTO canchaDTO) throws ResourceNotFoundException{
//        if(canchaRepository.findById(canchaDTO.getId()).isPresent()){
//            canchaToCanchaDTOConverter.convert(canchaRepository.save(canchaDTOaCanchaConverter.convert(canchaDTO)));
//            return canchaDTO;
//        }
//        else {
//            throw new ResourceNotFoundException("No se pudo actualizar correctamente la informacion");
//        }
//    }

    @Override
    public List<CanchaDTO> buscarXCategoria(String nombreCategoria)throws ResourceNotFoundException {
        Categoria categoria = categoriaRepository.findByNombre(nombreCategoria);
        if(canchaRepository.findByCategoria(categoria).size()>0) {
            List<CanchaDTO> canchaDTOS = new ArrayList<>();
            List<Cancha> canchaList = canchaRepository.findByCategoria(categoria);
            for (Cancha cancha : canchaList) {
                canchaDTOS.add(canchaToCanchaDTOConverter.convert(cancha));
            }
            return canchaDTOS;
        }
        else {
            throw new ResourceNotFoundException("No existe la categoria buscadad");
        }
    }

//    @Override
//    public List<CanchaDTO> buscarPorUser(String token) throws ResourceNotFoundException {
//        if(canchaRepository.findByUserEmail(jwtService.extractUserName(token)).size()>0) {
//            List<CanchaDTO> canchaDTOS = new ArrayList<>();
//            List<Cancha> canchaList = canchaRepository.findByUserEmail(jwtService.extractUserName(token));
//            for (Cancha cancha : canchaList) {
//                canchaDTOS.add(canchaToCanchaDTOConverter.convert(cancha));
//            }
//            return canchaDTOS;
//        }else {
//            throw new ResourceNotFoundException("No existen las canchas buscadas por el propietario");
//        }
//    }

    @Override
    public List<CanchaDTO> buscarPorUser(String token) throws ResourceNotFoundException {
        String userEmail = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(userEmail);
        List<Cancha> canchaList = canchaRepository.findByUser(user);
        if (!canchaList.isEmpty()) {
            List<CanchaDTO> canchaDTOS = new ArrayList<>();
            for (Cancha cancha : canchaList) {
                canchaDTOS.add(canchaToCanchaDTOConverter.convert(cancha));
            }

            return canchaDTOS;
        } else {
            throw new ResourceNotFoundException("No existen canchas buscadas por el propietario");
        }
    }



    @Override
    public List<CanchaDTO> buscarFiltrada(String barrio, String categoria) throws ResourceNotFoundException {
        List<Cancha> canchaList = canchaRepository.findCanchasByDeporteAndBarrio(barrio, categoria);
        List<CanchaDTO> canchaDTOList = new ArrayList<>();
        if (canchaList.size()>0){
            for (Cancha cancha : canchaList) {
                canchaDTOList.add(canchaToCanchaDTOConverter.convert(cancha));
            }
            return canchaDTOList;
        }
        throw new ResourceNotFoundException("No se econtro una lista con esos atributos");
    }

    public void agregarCanchaAFavoritos(Long id, String token) throws ResourceNotFoundException {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);
        CanchasFavoritas favoritesList = user.getCanchasFavoritas();
        if (favoritesList == null) {
            favoritesList = new CanchasFavoritas();
            favoritesList.setUser(user);
            user.setCanchasFavoritas(favoritesList);
        }
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cancha no encontrada"));
        List<Cancha> canchasFavoritas = favoritesList.getCanchas();
        if (!canchasFavoritas.contains(cancha)) {
            canchasFavoritas.add(cancha);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("La cancha ya está en la lista de favoritos");
        }
    }


    public void eliminarCanchaDeFavoritos(Long id, String token) throws ResourceNotFoundException {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);
        CanchasFavoritas favoritesList = user.getCanchasFavoritas();

        if (favoritesList != null) {
            Optional<Cancha> canchaOptional = canchaRepository.findById(id);

            if (canchaOptional.isPresent()) {
                Cancha cancha = canchaOptional.get();
                List<Cancha> canchasFavoritas = favoritesList.getCanchas();

                if (canchasFavoritas.contains(cancha)) {
                    canchasFavoritas.remove(cancha);
                    userRepository.save(user);
                } else {
                    throw new ResourceNotFoundException("La cancha no se encuentra en la lista de favoritos");
                }
            } else {
                throw new ResourceNotFoundException("Cancha no encontrada");
            }
        } else {
            throw new ResourceNotFoundException("La lista de favoritos no existe");
        }
    }

    @Override
    public boolean isCanchaFav(Long id, String token) {
        String email = jwtService.extractUserName(token);
        User user = userRepository.findByEmail(email);

        if (user != null) {
            List<Cancha> canchasFavoritas = user.getCanchasFavoritas().getCanchas();
            for (Cancha cancha : canchasFavoritas) {
                if (cancha.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }





}
