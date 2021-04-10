package com.example.demo.domain.customer;

import com.example.demo.domain.customer.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void addNormalCustomer() throws Exception {

        Map req = new HashMap<String, Object>() {{
            put("name", "person1");
            put("gender", "MAN");
            put("type", "NORMAL");
        }};

        ResultActions result = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        );

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    void addDisabledCustomer() throws Exception {

        Map disabledInfo = new HashMap<String, Object>() {{
            put("isCommunicable", true);
            put("handicapInfo", "다리");
        }};

        Map req = new HashMap<String, Object>() {{
            put("name", "person2");
            put("gender", "WOMAN");
            put("type", "DISABLED");
            put("disabledInfo", disabledInfo);
        }};

        ResultActions result = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        );

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    void addPregnantCustomer() throws Exception {

        Map<String, Object> pregnantInfo = Map.of(
        "babyDue", "2020-10-09"
        );

        Map<String, Object> req = Map.of(
                "name", "person3",
                "gender", "WOMAN",
                "type", "PREGNANT",
                "pregnantInfo", pregnantInfo
        );

        ResultActions result = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        );

        result.andDo(print());
        result.andExpect(status().isOk());
    }



    @Test @Disabled
    void getCustomers() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/customers?page=2&size=2&sort=updated,DESC")
                .contentType(MediaType.APPLICATION_JSON));

        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    void getCustomerById() throws Exception {
        String customerId = "20";
        ResultActions result = mockMvc.perform(get("/api/customers/"+customerId));
//                .contentType(MediaType.APPLICATION_JSON));

        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }
}