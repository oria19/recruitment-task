package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.controller.CalculationController;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesRegex;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationControllerTest {

    private final CalculationController calculationController;
    private final MockMvc mockMvc;

    @Autowired
    public CalculationControllerTest(CalculationController calculationController, MockMvc mockMvc) {
        this.calculationController = calculationController;
        this.mockMvc = mockMvc;
    }

    @Test
    public void contextLoad() {
        assertNotNull(calculationController);
        assertNotNull(mockMvc);
    }

    @Test
    public void shouldReturnNumber() throws Exception {
        this.mockMvc.perform(get("/result")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(matchesRegex("^\\d*\\.?\\d*$")));
    }
}
