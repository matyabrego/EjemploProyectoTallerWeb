package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioUsuario;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorRegistro(ServicioUsuario servicioUsuario){
        this.servicioUsuario=servicioUsuario;

    }
    public ModelAndView registrar(String email) {

        if(email.isEmpty()){
            ModelMap modelo = new ModelMap();
            modelo.put("error", "El email es obligatorio");
            return new ModelAndView("registro", modelo);
        }
        try{
            servicioUsuario.registrar(email, "");
        }catch(UsuarioExistente ex){
            ModelMap modelo = new ModelMap();
            modelo.put("error", "El email es obligatorio");
            return new ModelAndView("registro", modelo);

        }

        return new ModelAndView("redirect:/login");
    }
}
