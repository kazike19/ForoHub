package com.forohub.ForoHub.controller;

import com.forohub.ForoHub.domain.Usuario.*;
import com.forohub.ForoHub.infra.security.DatosTokenJWT;
import com.forohub.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegistroUsuarioService registroUsuarioService;

    @PostMapping("/login")
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAuth datos){
        var autentificacionToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena());
        var autentificacion = manager.authenticate(autentificacionToken);

        // tokenService.generarToken((Usuario) autentificacion.getPrincipal())

        var tokenJWT = tokenService.generarToken((UsuarioModel) autentificacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));

    }

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){

        DatosDetalleUsuario datosDetalleUsuario = registroUsuarioService.registrar(datos);

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(datosDetalleUsuario.id()).toUri();
        return ResponseEntity.created(url).body(datosDetalleUsuario);

    }


}
