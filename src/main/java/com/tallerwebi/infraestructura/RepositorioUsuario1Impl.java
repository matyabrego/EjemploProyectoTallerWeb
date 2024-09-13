package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario1;
import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuario1Impl implements RepositorioUsuario1 {

    @Override
    public Usuario buscar(String email) {
        return null;
    }

    @Override
    public void guardar(Usuario usuarioCreado) {

    }
}
