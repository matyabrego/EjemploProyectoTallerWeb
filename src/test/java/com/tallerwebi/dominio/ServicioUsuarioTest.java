package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import com.tallerwebi.infraestructura.RepositorioUsuario1Impl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    RepositorioUsuario1 repositorioUsuario1 = mock(RepositorioUsuario1Impl.class);
    ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario1);

    @Test
    public void siExisteEmailYPasswordElRegistroEsExitoso() {

        //PREPARCION
        //EJECUCION
        Usuario usuarioCreado = cuandoRegistroUsuario("maty@gmail.com", "12345");
        //VALIDACION
        entoncesElRegistroEsExitoso(usuarioCreado);
    }

    private Usuario cuandoRegistroUsuario(String email, String password) {

        Usuario usuarioCreado = servicioUsuario.registrar(email, password);
        return usuarioCreado;

    }

    private void entoncesElRegistroEsExitoso(Usuario usuarioCreado) {

        assertThat(usuarioCreado, notNullValue());
        verify(repositorioUsuario1, times(1)).guardar(usuarioCreado);//verify se usa solo para objetos mock

    }

    @Test
    public void siLaPasswordTieneMenosDeCincoCaracteresElRegistroFalla(){

        //PREPARACION
        //EJECUCION
        //Usuario usuarioCreado = cuandoRegistroUsuario("maty@gmail.com", "123");
        assertThrows(PasswordLongitudIncorrecta.class, ()-> cuandoRegistroUsuario("maty@gmail.com", "123"));
        //VALIDACION
        //entoncesElRegistroFalla(usuarioCreado);
    }

    private void entoncesElRegistroFalla(Usuario usuarioCreado) {
        assertThat(usuarioCreado,nullValue());
    }

    @Test
    public void siYaExisteUsuarioConMismoMailElRegistroFalla(){

        siExisteUsuario("maty@gmail.com", "12345");

        when(repositorioUsuario1.buscar("maty@gmail.com")).thenReturn(new Usuario());

        Usuario usuarioCreado = cuandoRegistroUsuario("maty@gmail.com", "12345");
        
        entoncesElRegistroFalla(usuarioCreado);

    }

    private void siExisteUsuario(String email, String password) {
        cuandoRegistroUsuario(email, password);
    }


}
