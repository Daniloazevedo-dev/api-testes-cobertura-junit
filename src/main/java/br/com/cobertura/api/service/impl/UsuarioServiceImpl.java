package br.com.cobertura.api.service.impl;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.repository.UsuarioRepository;
import br.com.cobertura.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElse(null);
    }
}
