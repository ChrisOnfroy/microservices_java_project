package com.example.client_microservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientMicroservicesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void roleAndClientCrudFlowWorks() throws Exception {
        String rolePayload = """
                {
                  "name": "ADMIN"
                }
                """;

        String roleResponse = mockMvc.perform(post("/v1/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rolePayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.name").value("ADMIN"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String roleUuid = roleResponse.replaceAll(".*\"uuid\":\"([^\"]+)\".*", "$1");

        String clientPayload = """
                {
                  "username": "ian.user",
                  "password": "Password123",
                  "email": "ian@example.com",
                  "roleUuid": "%s"
                }
                """.formatted(roleUuid);

        String clientResponse = mockMvc.perform(post("/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.roleUuid").value(roleUuid))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String clientUuid = clientResponse.replaceAll(".*\"uuid\":\"([^\"]+)\".*", "$1");

        mockMvc.perform(get("/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        String updatedRolePayload = """
                {
                  "name": "SUPERVISOR"
                }
                """;

        mockMvc.perform(put("/v1/roles/{uuid}", roleUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedRolePayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("SUPERVISOR"));

        String updatedClientPayload = """
                {
                  "username": "ian.admin",
                  "password": "NewPassword123",
                  "email": "ian.admin@example.com",
                  "status": false,
                  "roleUuid": "%s"
                }
                """.formatted(roleUuid);

        mockMvc.perform(put("/v1/clients/{uuid}", clientUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedClientPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("ian.admin"))
                .andExpect(jsonPath("$.roleName").value("SUPERVISOR"))
                .andExpect(jsonPath("$.status").value(false));

        mockMvc.perform(delete("/v1/clients/{uuid}", clientUuid))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/v1/roles/{uuid}", roleUuid))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/v1/clients/{uuid}", clientUuid))
                .andExpect(status().isNotFound());
    }
}
