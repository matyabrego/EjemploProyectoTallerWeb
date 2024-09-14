package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    RepositorioUsuario1 repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario1 repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }
    @Override
    public Usuario registrar(String email, String password) {

        if (password.length() < 5) {
            throw new PasswordLongitudIncorrecta();
        }
        Usuario usuarioBuscado = repositorioUsuario.buscar("maty2");
        if(usuarioBuscado != null){
            return null;

        }
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);
        repositorioUsuario.guardar(usuario);
        return usuario;
    }
}
