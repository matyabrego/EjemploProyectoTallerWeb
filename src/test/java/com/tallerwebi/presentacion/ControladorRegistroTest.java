package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.ServicioUsuarioImpl;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class ControladorRegistroTest {

    ServicioUsuario servicioUsuario = mock(ServicioUsuarioImpl.class);
    ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuario);

    @Test
    public void siExxisteEmailYPasswordElRegistroEsExitoso(){
        
        //PREPARACION
        NoExisteUsuario();
        //EJECUCION
        ModelAndView modelAndView =RegistroUsuario("maty@gmail.com");
        //VALIDACION
        ElRegistroEsExitoso(modelAndView);
        
    }

    private void ElRegistroEsExitoso(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/login"));
    }

    private ModelAndView RegistroUsuario(String email) {

        ModelAndView modelAndView =controladorRegistro.registrar(email);
        return modelAndView;
    }

    private void NoExisteUsuario() {
    }

    @Test
    public void siElEmailEstaVacioElRegistroFalla(){

        //PREPARACION
        NoExisteUsuario();
        //EJECUCION
        String emailVacio = "";
        ModelAndView modelAndView = RegistroUsuario(emailVacio);
        //VALIDACION
        elRegistroFalla(modelAndView, "El email es obliagatorio");
    }

    private void elRegistroFalla(ModelAndView modelAndView, String mensaje) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("registro"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase( "El email es obligatorio"));
    }

    @Test
    public void siLasPasswordSonDistintasElRegistroFalla(){

    }

    @Test
    public void siExisteUsuarioConEmailDelRegistroElRegistroFalla(){

        when(servicioUsuario.registrar("maty@gmail.com","")).thenThrow(UsuarioExistente.class);
        ModelAndView modelAndView =RegistroUsuario("maty@gmail.com");
        elRegistroFalla(modelAndView, "El usuario ya existe");
    }

}
