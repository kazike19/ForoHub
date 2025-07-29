package com.forohub.ForoHub.domain.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email
    ) {

    public DatosDetalleUsuario(UsuarioModel usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
