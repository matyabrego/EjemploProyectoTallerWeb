package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioCalculadora;
import com.tallerwebi.dominio.excepcion.ExceptionCalculadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCalculadora {

    private ServicioCalculadora servicioCalculadora;

    @Autowired
    public ControladorCalculadora(ServicioCalculadora servicioCalculadora){
        this.servicioCalculadora=servicioCalculadora;

    }

    @RequestMapping(path = "/mostrarcalculadora", method = RequestMethod.GET)
    public ModelAndView mostrarCalculadora() {
        return new ModelAndView("mostrarcalculadora");
    }

    @RequestMapping(path = "/calcular")
    public ModelAndView calcular(@RequestParam(name="numero1") Integer num1, @RequestParam(name="numero2") Integer num2, @RequestParam(name="operacion", required = false) String operacion) {

        ModelMap modelo = new ModelMap();

        Double resultado = null;
        try {
            resultado = servicioCalculadora.resolverOperacion(num1, num2, operacion);
        } catch (ExceptionCalculadora e) {

            String error = e.getMessage();
            modelo.put("error", error);
            return new ModelAndView("resultadocalculadora", modelo);
        }

        modelo.put("resul", resultado);
        modelo.put("var1", num1);

        modelo.put("var2", num2);
        modelo.put("operacion", operacion);

        return new ModelAndView("resultadocalculadora", modelo);

    }
}





