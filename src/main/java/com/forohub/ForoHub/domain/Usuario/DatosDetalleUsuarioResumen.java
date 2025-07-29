package com.forohub.ForoHub.domain.Usuario;

public record DatosDetalleUsuarioResumen(
        Long id,
        String nombre,
        String email
        ) {
    public DatosDetalleUsuarioResumen(UsuarioModel usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
