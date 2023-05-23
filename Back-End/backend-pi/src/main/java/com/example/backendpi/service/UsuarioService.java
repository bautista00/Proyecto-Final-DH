package com.example.backendpi.service;

import com.example.backendpi.domain.Usuario;
import com.example.backendpi.dto.ClienteGuardar;
import com.example.backendpi.dto.PrestadorGuardar;
import com.example.backendpi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userOptional = usuarioRepository.findByEmail(username);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else {
            throw new UsernameNotFoundException("Error no se encontro ningun usuario con el nombre:"+username);
        }
    }

    public ClienteGuardar convertirUsuarioACLienteGuardar(Usuario usuario){
        ClienteGuardar clienteGuardar = new ClienteGuardar();
        clienteGuardar.setId(usuario.getId());
        clienteGuardar.setEmail(usuario.getEmail());
        clienteGuardar.setNombre(usuario.getNombre());
        clienteGuardar.setPassword(usuario.getPassword());
        clienteGuardar.setTelefono(usuario.getTelefono());
        return clienteGuardar;
    }

   public PrestadorGuardar convertirUsuarioAPrestadorGuardar(Usuario usuario){
        PrestadorGuardar prestadorGuardar = new PrestadorGuardar();
        prestadorGuardar.setId(usuario.getId());
        prestadorGuardar.setCBU(usuario.getCBU());
        prestadorGuardar.setCuil(usuario.getCuil());
        prestadorGuardar.setEmail(usuario.getEmail());
        prestadorGuardar.setPassword(usuario.getPassword());
        prestadorGuardar.setTelefono(usuario.getTelefono());
        prestadorGuardar.setNombre(usuario.getNombre());
        return prestadorGuardar;
   }
}
