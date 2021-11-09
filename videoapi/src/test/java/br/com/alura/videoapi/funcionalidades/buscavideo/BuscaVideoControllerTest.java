package br.com.alura.videoapi.funcionalidades.buscavideo;

import br.com.alura.videoapi.entidades.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BuscaVideoControllerTest {

    private URI uri;
    private String json;

    @Value("${br.com.alura.videoapi.uri}")
    private String uriController;

    @Autowired
    BuscaVideoRepository videoRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void antes(){
        Video video1 = new Video("Spring Boot e API REST", "descricao", "https://cursos.alura.com.br/course/spring-boot-api-rest");
        Video video2 = new Video("Spring Data JPA", "descricao", "http://www.springdata.com.br");

        videoRepository.save(video1);
        videoRepository.save(video2);
    }

    @Test
    @DisplayName("deve retornar status 200 aos buscar videos cadastrados")
    public void teste1() throws Exception {

        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .get(uri);

        List<Video> all = videoRepository.findAll();
        Assertions.assertEquals(2, all.size());
        Assertions.assertEquals("Spring Boot e API REST", all.get(0).getTitulo());

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}