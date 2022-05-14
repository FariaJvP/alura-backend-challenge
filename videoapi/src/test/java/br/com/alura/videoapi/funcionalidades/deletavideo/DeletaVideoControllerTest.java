package br.com.alura.videoapi.funcionalidades.deletavideo;

import br.com.alura.videoapi.entidades.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DeletaVideoControllerTest {

    private URI uri;
    
    @Autowired
    DeletaVideoRepository videoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        Optional<Video> videoBuscadoAntes = videoRepository.findById(UUID.fromString("32000000-0000-0000-0000-000000000000"));
        Assertions.assertNotNull(videoBuscadoAntes);

        uri = new URI("/videos/32000000-0000-0000-0000-000000000000");
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .delete(uri);

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<Video> videoBuscado = videoRepository.findById(UUID.fromString("32000000-0000-0000-0000-000000000000"));
        Assertions.assertTrue(videoBuscado.isEmpty());

    }
}