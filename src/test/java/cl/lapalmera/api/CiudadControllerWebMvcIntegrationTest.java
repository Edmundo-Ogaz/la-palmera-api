package cl.lapalmera.api;

import cl.lapalmera.api.controller.CiudadController;
import cl.lapalmera.api.controller.ComunaController;
import cl.lapalmera.api.model.UserModel;
import cl.lapalmera.api.repository.CiudadRepository;
import cl.lapalmera.api.repository.ComunaRepository;
import cl.lapalmera.api.repository.UserRepository;
import cl.lapalmera.api.service.CiudadService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CiudadController.class)
public class CiudadControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CiudadService comunaService;

    @MockBean
    private CiudadRepository comunaRepository;

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
                        .get("/ciudades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
        )
                .andExpect(status().isOk())
                .andReturn();
    }
}
