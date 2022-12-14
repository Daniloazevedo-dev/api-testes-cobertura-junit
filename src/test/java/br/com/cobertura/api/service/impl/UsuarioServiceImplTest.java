package br.com.cobertura.api.service.impl;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.domain.dto.UsuarioDTO;
import br.com.cobertura.api.repository.UsuarioRepository;
import br.com.cobertura.api.service.exceptions.DataIntegratyViolationException;
import br.com.cobertura.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Danilo";
    public static final String EMAIL = "danilo.ann@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado.";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper mapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private Optional<Usuario> optionalUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenFindByIdThenReturnAnUsuarioInstance() {
        when(usuarioRepository.findById(anyInt())).thenReturn(optionalUsuario);
        Usuario response = usuarioService.findById(ID);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(usuarioRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            usuarioService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> response = usuarioService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Usuario.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(usuarioRepository.save(any())).thenReturn(usuario);

        Usuario response = usuarioService.create(usuarioDTO);
        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuario);

        try {
            optionalUsuario.get().setId(2);
            usuarioService.create(usuarioDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(usuarioRepository.save(any())).thenReturn(usuario);

        Usuario response = usuarioService.update(usuarioDTO);
        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(optionalUsuario);

        try {
            optionalUsuario.get().setId(2);
            usuarioService.create(usuarioDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(usuarioRepository.findById(anyInt())).thenReturn(optionalUsuario);
        doNothing().when(usuarioRepository).deleteById(anyInt());
        usuarioService.delete(ID);
        verify(usuarioRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(usuarioRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            usuarioService.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    private void startUsuario() {
        usuario = new Usuario(ID, NAME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUsuario = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));
    }
}