package com.train.ticket.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.ticket.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TicketService ticketService;

    @Test
    public void testPurchaseTicket() throws Exception {
        // Read JSON file and parse request data
        String jsonContent = new String(Files.readAllBytes(Paths.get("E:/New folder/train-ticketing-system/postman_collection/TrainTicketManager.postman_collection.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        JsonNode itemsNode = rootNode.get("item");

        // Find the request with name "PurchaseTicket"
        JsonNode purchaseTicketRequest = null;
        for (JsonNode itemNode : itemsNode) {
            if ("PurchaseTicket".equals(itemNode.get("name").asText())) {
                purchaseTicketRequest = itemNode.get("request");
                break;
            }
        }

        // Extract request data
        String from = purchaseTicketRequest.get("body").get("raw").asText();

        JsonNode parsedBody = objectMapper.readTree(from);

// Access the user field from the body
        JsonNode userNode = parsedBody.get("user");
        String firstName = userNode.get("firstName").asText();
        String lastName = userNode.get("lastName").asText();
        String email = userNode.get("email").asText();
        String fromValue = objectMapper.readTree(from).get("from").asText();
        String toValue = objectMapper.readTree(from).get("to").asText();

        // Construct mock HTTP request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ticket/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(from);

        // Perform the mock HTTP request
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isBadRequest());
        } else {
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.from").value(fromValue))
                    .andExpect(jsonPath("$.to").value(toValue))
                    .andExpect(jsonPath("$.user.firstName").value(firstName))
                    .andExpect(jsonPath("$.user.lastName").value(lastName))
                    .andExpect(jsonPath("$.user.email").value(email));
        }


    }
}

