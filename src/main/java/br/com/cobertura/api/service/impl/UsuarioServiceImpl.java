package br.com.cobertura.api.service.impl;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.domain.dto.UsuarioDTO;
import br.com.cobertura.api.repository.UsuarioRepository;
import br.com.cobertura.api.service.UsuarioService;
import br.com.cobertura.api.service.exceptions.DataIntegratyViolationException;
import br.com.cobertura.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario create(UsuarioDTO obj) {
        findByEmail(obj);
        return usuarioRepository.save(mapper.map(obj, Usuario.class));
    }

    private void findByEmail(UsuarioDTO obj) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(obj.getEmail());
        if(usuario.isPresent()) {
            throw  new DataIntegratyViolationException("E-mail já cadastrado no sistema.");
        }
    }
}
