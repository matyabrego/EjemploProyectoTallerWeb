package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ExceptionCalculadora;

public interface ServicioCalculadora {
    Double resolverOperacion(Integer num1, Integer num2, String operacion) throws ExceptionCalculadora;
}
