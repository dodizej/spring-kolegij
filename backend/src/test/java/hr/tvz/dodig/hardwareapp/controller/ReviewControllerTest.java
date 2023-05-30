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

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

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
    void getAllReviews() throws Exception {
        this.mockMvc.perform(
                        get("/review")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8));
    }

    @Test
    void getReviewByHardwareCode() throws Exception {
        this.mockMvc.perform(
                        get("/review/ASD")
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getJwtToken())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$[0].naslov").value("Dobro"))
                .andExpect(jsonPath("$[0].tekst").value("Lorem ipsum dobro"))
                .andExpect(jsonPath("$[0].ocjena").value(4));
    }
}