package com.forohub.ForoHub.domain.Usuario;

import com.forohub.ForoHub.domain.ValidacionExcepcion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos){
        if(usuarioRepository.findByEmail(datos.email()) != null){
            throw new ValidacionExcepcion("El email "+ datos.email() +" ya esta registrado");
        }

        String contrasenaHasheada = passwordEncoder.encode(datos.contrasena());

        UsuarioModel nuevoUsuario = new UsuarioModel(
                datos.nombre(),
                datos.email(),
                contrasenaHasheada
        );

        UsuarioModel usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return new DatosDetalleUsuario(usuarioGuardado);
    }
}
