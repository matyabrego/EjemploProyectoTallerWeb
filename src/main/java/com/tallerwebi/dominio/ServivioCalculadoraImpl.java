package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ExceptionCalculadora;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Service("servivioCalculadora")
@Transactional
public class ServivioCalculadoraImpl implements ServicioCalculadora {

    @Override
    public Double resolverOperacion(Integer num1, Integer num2, String operacion) throws ExceptionCalculadora {

        Double resultado = 0.0;

        if (operacion == null) {
            throw new ExceptionCalculadora("Operacion no valida");
        }
        if (operacion.equals("suma")) {
            resultado = (double) (num1 + num2);
        } else if (operacion.equals("resta")) {
            resultado = (double) (num1 - num2);
        } else if (operacion.equals("multiplicacion")) {
            resultado = (double) (num1 * num2);
        } else if (operacion.equals("division")) {
            if (num2 == 0 || num2 == null) {
                throw new ExceptionCalculadora("El denominador no puede ser cero ni nulo");
            }
            resultado = (double) (num1 / num2);
        }

        return resultado;
    }
}