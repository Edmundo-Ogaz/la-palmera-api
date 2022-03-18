package cl.lapalmera.api;

import cl.lapalmera.api.controller.ComunaController;
import cl.lapalmera.api.controller.UsuarioController;
import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.model.UserModel;
import cl.lapalmera.api.repository.ComunaRepository;
import cl.lapalmera.api.repository.UserRepository;
import cl.lapalmera.api.service.ComunaService;
import cl.lapalmera.api.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComunaController.class)
public class ComunaControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ComunaService comunaService;

    @MockBean
    private ComunaRepository comunaRepository;

    @Test
    public void givenAuthRequestOnPrivateService_All_shouldSucceedWith200() throws Exception {

        UserModel customerModel = new UserModel(1l, "test", "test", "test@test.cl", "$2a$12$m736W0KE7HFXuWnp9m534OOE3VPVgwza19h.hBoLhP.L4Eoc5Nnqa");
        Mockito.when(userRepository.findByUsername("test@test.cl")).thenReturn(customerModel);

        JSONObject dataLogin = new JSONObject();
        dataLogin.put("username", "test@test.cl");
        dataLogin.put("password", "password");

        MvcResult resultLogin = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/services/controller/user/login")
                        .content(dataLogin.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        String content = resultLogin.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        String token = (String)json.get("token");
        Assert.notNull(token);

        Mockito.when(userRepository.save(any())).thenReturn(customerModel);

        JSONObject data = new JSONObject();
        data.put("password", "test");
        data.put("firstName", "test");
        data.put("lastName", "test");
        data.put("email", "test@test.cl");

        String authorization = "Bearer " + token;

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .get("/comunas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void givenAuthRequestOnPrivateGetService_Search_shouldSucceedWith200() throws Exception {

        UserModel customerModel = new UserModel(1l, "test", "test", "test@test.cl", "$2a$12$m736W0KE7HFXuWnp9m534OOE3VPVgwza19h.hBoLhP.L4Eoc5Nnqa");
        Mockito.when(userRepository.findByUsername("test@test.cl")).thenReturn(customerModel);

        JSONObject dataLogin = new JSONObject();
        dataLogin.put("username", "test@test.cl");
        dataLogin.put("password", "password");

        MvcResult resultLogin = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/services/controller/user/login")
                        .content(dataLogin.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        String content = resultLogin.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        String token = (String)json.get("token");
        Assert.notNull(token);

        Mockito.when(userRepository.save(any())).thenReturn(customerModel);

        String authorization = "Bearer " + token;

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .get("/comunas/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void givenAuthRequestOnPrivatePostService_Comunas_shouldSucceedWith200() throws Exception {

        UserModel customerModel = new UserModel(1l, "test", "test", "test@test.cl", "$2a$12$m736W0KE7HFXuWnp9m534OOE3VPVgwza19h.hBoLhP.L4Eoc5Nnqa");
        Mockito.when(userRepository.findByUsername("test@test.cl")).thenReturn(customerModel);

        JSONObject dataLogin = new JSONObject();
        dataLogin.put("username", "test@test.cl");
        dataLogin.put("password", "password");

        MvcResult resultLogin = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/services/controller/user/login")
                        .content(dataLogin.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        String content = resultLogin.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        String token = (String)json.get("token");
        Assert.notNull(token);

        Comuna comuna = new Comuna("code", "name", "city");
        Mockito.when(comunaRepository.save(any())).thenReturn(comuna);

        String authorization = "Bearer " + token;

        JSONObject data = new JSONObject();
        dataLogin.put("code", "R02C01C05");
        dataLogin.put("name", "Antofagasta");
        dataLogin.put("cityCode", "R02C01");

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/comunas")
                        .content(data.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void givenAuthRequestOnPrivatePutService_Comunas_shouldSucceedWith200() throws Exception {

        UserModel customerModel = new UserModel(1l, "test", "test", "test@test.cl", "$2a$12$m736W0KE7HFXuWnp9m534OOE3VPVgwza19h.hBoLhP.L4Eoc5Nnqa");
        Mockito.when(userRepository.findByUsername("test@test.cl")).thenReturn(customerModel);

        JSONObject dataLogin = new JSONObject();
        dataLogin.put("username", "test@test.cl");
        dataLogin.put("password", "password");

        MvcResult resultLogin = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/services/controller/user/login")
                        .content(dataLogin.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        String content = resultLogin.getResponse().getContentAsString();
        JSONObject json = new JSONObject(content);
        String token = (String)json.get("token");
        Assert.notNull(token);

        Comuna comuna = new Comuna("code", "name", "city");
        Mockito.when(comunaRepository.save(any())).thenReturn(comuna);

        String authorization = "Bearer " + token;

        JSONObject data = new JSONObject();
        dataLogin.put("code", "R02C01C05");
        dataLogin.put("name", "Antofagasta");
        dataLogin.put("cityCode", "R02C01");

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders
                        .put("/comunas")
                        .content(data.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
        )
                .andExpect(status().isOk())
                .andReturn();
    }
}
