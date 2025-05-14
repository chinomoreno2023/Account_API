package com.accountapi.controller;

import com.accountapi.service.account.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransferControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transferController)
                .build();
    }

    @Test
    void testGetInvalidJson_shouldReturnBadRequest() throws Exception {
        String json = """
            {
              "value": 100.0
            }
            """;

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetNegativeValue_shouldReturnBadRequest() throws Exception {
        String json = """
            {
              "to": 2,
              "value": -10
            }
            """;

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testValidTransfer_ShouldReturnOk() throws Exception {
        String json = """
            {
              "to": 2,
              "value": 100.0
            }
            """;

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(1L);

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .principal(auth))
                .andExpect(status().isOk());
    }

    @Test
    void testValidTransfer_ShouldReturnCorrectMessage() throws Exception {
        String json = """
        {
          "to": 2,
          "value": 100.0
        }
        """;

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(1L);

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .principal(auth))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Transfer from user 1 to user 2 was successful"));
    }

    @Test
    void testGetZeroValue_shouldReturnBadRequest() throws Exception {
        String json = """
        {
          "to": 2,
          "value": 0
        }
        """;

        mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
