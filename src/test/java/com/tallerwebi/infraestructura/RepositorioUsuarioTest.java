package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario1;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioUsuarioTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioUsuario1 repositorioUsuario1;

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnUsuario() {

        Usuario usuario = new Usuario();
        usuario.setEmail("maty@gmail.com");
        usuario.setPassword("12345");
        //WHEN
        repositorioUsuario1.guardar(usuario);
        //THEN
        assertThat(usuario.getId(), notNullValue());

    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarUsuarioPorEmail() {
        //GIVEN
        Usuario usuario = new Usuario();
        usuario.setEmail("maty@gmail.com");
        usuario.setPassword("12345");

        sessionFactory.getCurrentSession().save(usuario);
        //GIVEN 2
        Usuario usuario2 = new Usuario();
        usuario2.setEmail("maty2@gmail.com");
        usuario2.setPassword("5678");

        sessionFactory.getCurrentSession().save(usuario2);

        //WHEN
        Usuario buscado = repositorioUsuario1.buscar("maty2@gmail.com");

        //THEN
        assertThat(buscado, notNullValue());
        assertThat(buscado.getPassword(), equalToIgnoringCase("5678"));

    }
}

