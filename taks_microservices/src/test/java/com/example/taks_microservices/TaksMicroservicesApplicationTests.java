package com.example.taks_microservices;

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
class TaksMicroservicesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void taskCrudFlowWorks() throws Exception {
        String createPayload = """
                {
                  "name": "Prepare report",
                  "description": "Prepare the monthly report",
                  "points": 8,
                  "deadline": "2099-12-31"
                }
                """;

        String createdResponse = mockMvc.perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Prepare report"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String uuid = createdResponse.replaceAll(".*\"uuid\":\"([^\"]+)\".*", "$1");

        mockMvc.perform(get("/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        String updatePayload = """
                {
                  "name": "Prepare final report",
                  "description": "Prepare the updated monthly report",
                  "points": 13,
                  "status": false,
                  "deadline": "2099-12-31"
                }
                """;

        mockMvc.perform(put("/v1/tasks/{uuid}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Prepare final report"))
                .andExpect(jsonPath("$.status").value(false));

        mockMvc.perform(delete("/v1/tasks/{uuid}", uuid))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/v1/tasks/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }
}
