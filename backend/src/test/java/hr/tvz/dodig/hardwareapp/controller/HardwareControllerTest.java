package hr.tvz.dodig.hardwareapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.dodig.hardwareapp.security.command.LoginCommand;
import hr.tvz.dodig.hardwareapp.security.controller.AuthenticationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    private String jwtToken;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String getJwtToken() {
        if (jwtToken == null) {
            this.jwtToken = authenticationController.login(new LoginCommand("admin", "admin")).getJwt();
        }
        return this.jwtToken;
    }

    @Test
    void getAllHardware() throws Exception {
        this.mockMvc.perform(
                            get("/hardware")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .accept(MediaType.APPLICATION_JSON)
                            )
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().encoding(StandardCharsets.UTF_8));
    }

    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/ASD")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value("NVIDIA GTX 980 Ti"))
                .andExpect(jsonPath("$.price").value(200.0));
    }

    @Test
    @Transactional
    void save() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("code", "DFG");
        body.put("name", "INTEL I9-9900K");
        body.put("type", "CPU");
        body.put("numberOfComponents", "1");
        body.put("price", 1100.0);

        this.mockMvc.perform(
                        post("/hardware")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.code").value("DFG"))
                .andExpect(jsonPath("$.name").value("INTEL I9-9900K"))
                .andExpect(jsonPath("$.price").value(1100.0));
    }

    @Test
    @Transactional
    void delete() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/hardware/RTZ")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void put() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("code", "ASD");
        body.put("name", "NVIDIA RTX 2060");
        body.put("type", "GPU");
        body.put("numberOfComponents", "1");
        body.put("price", 9999.0);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/hardware/ASD")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.code").value("ASD"))
                .andExpect(jsonPath("$.name").value("NVIDIA RTX 2060"))
                .andExpect(jsonPath("$.price").value(9999.0));
    }
}