package br.com.cobertura.api.resource;

import br.com.cobertura.api.domain.Usuario;
import br.com.cobertura.api.domain.dto.UsuarioDTO;
import br.com.cobertura.api.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Danilo";
    public static final String EMAIL = "danilo.ann@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado.";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";

    @InjectMocks
    private UsuarioResource usuarioResource;

    @Mock
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private ModelMapper mapper;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsuario();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(usuarioServiceImpl.findById(anyInt())).thenReturn(usuario);
        when(mapper.map(any(), any())).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsuario() {
        usuario = new Usuario(ID, NAME, EMAIL, PASSWORD);
        usuarioDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
    }
}