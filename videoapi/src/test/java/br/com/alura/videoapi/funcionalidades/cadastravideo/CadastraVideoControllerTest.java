package br.com.alura.videoapi.funcionalidades.cadastravideo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CadastraVideoControllerTest {

    private URI uri;
    private String json;

    @Value("${br.com.alura.videoapi.uri}")
    private String uriController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private StringSample stringSample = new StringSample();

    @Test
    @DisplayName("deve passar no teste e retornar 201 ao submeter um formulário válido") //teste sem a lib do Jackson
    public void teste1() throws Exception {
        uri = new URI(uriController);
        json = "{\"titulo\":\"qualquer titulo\",\"descricao\":\"qualquer descricao\",\"url\":\"https://cursos.alura.com.br/course/spring-boot-profiles-testes-deploy\"}";
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    @DisplayName("deve passar no teste e retornar 201 ao submeter um formulário válido") //teste com a lib do Jackson
    public void teste2() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom26Caracteres(),
                                stringSample.getStringCom200Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("deve retornar status 400 ao realizar uma requisição com url inválida")
    public void teste3() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest("TDD", "Test Driven Design com Junit 5",
                                "www.foo.com.br"))); //url inválida

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("deve retornar 400 caso o titulo possua mais de 80 caracteres")
    public void teste4() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom200Caracteres(),
                                stringSample.getStringCom200Caracteres(),
                                "http://www.foo.com.br")));
    }

    @Test
    @DisplayName("deve retornar 400 caso o titulo possua 81 caracteres")
    public void teste5() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom81Caracteres(),
                                stringSample.getStringCom200Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }

    @Test
    @DisplayName("deve retornar 201 caso o titulo tenha exatamente 80 caracteres")
    public void teste6() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringComExatamente80Caracteres(),
                                stringSample.getStringCom200Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

    }

    @Test
    @DisplayName("deve retornar 201 caso o titulo possua 79 caracteres")
    public void teste7() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom79Caracteres(),
                                stringSample.getStringCom200Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

    }

    @Test
    @DisplayName("deve retornar 400 caso a descrição possua mais de 400 caracteres")
    public void teste8() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom26Caracteres(),
                                stringSample.getStringComMaisde400Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("deve retornar 400 caso a descrição possua 401 caracteres")
    public void teste9() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom26Caracteres(),
                                stringSample.getStringCom401Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("deve retornar 201 caso a descrição possua exatamente 400 caracteres")
    public void teste10() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom26Caracteres(),
                                stringSample.getStringComExatamente400Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("deve retornar 201 caso a descrição possua 399 caracteres")
    public void teste11() throws Exception {
        uri = new URI(uriController);
        MockHttpServletRequestBuilder requisicaoParaOController = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writeValueAsString(new NovoVideoRequest(stringSample.getStringCom26Caracteres(),
                                stringSample.getStringCom399Caracteres(),
                                "http://www.foo.com.br")));

        mockMvc.perform(requisicaoParaOController)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

}