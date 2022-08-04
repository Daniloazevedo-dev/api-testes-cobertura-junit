package br.com.cobertura.api.service;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Integer id);

    List<Usuario> findAll();

    Usuario create(UsuarioDTO obj);

}
