package br.com.cobertura.api.resource;

import br.com.cobertura.api.domain.dto.UsuarioDTO;
import br.com.cobertura.api.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    public static final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = ID)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(usuarioService.findById(id), UsuarioDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok()
                .body(usuarioService.findAll()
                        .stream().map(x -> mapper
                                .map(x,UsuarioDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO obj) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID)
                 .buildAndExpand(usuarioService.create(obj).getId()).toUri()).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(usuarioService.update(obj), UsuarioDTO.class));
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
