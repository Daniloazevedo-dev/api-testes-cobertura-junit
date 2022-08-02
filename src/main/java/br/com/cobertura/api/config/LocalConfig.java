package br.com.cobertura.api.config;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public void startDB() {
        Usuario u1 = new Usuario(null, "Danilo", "danilo.ann@gmail.com", "123");
        Usuario u2 = new Usuario(null, "Denilson", "denilson@gmail.com", "154");

        usuarioRepository.saveAll(List.of(u1,u2));
    }

}
