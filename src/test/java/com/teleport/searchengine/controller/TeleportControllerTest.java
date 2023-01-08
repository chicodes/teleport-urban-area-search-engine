package com.teleport.searchengine.controller;

import com.teleport.searchengine.ExcerciseApplication;
import com.teleport.searchengine.dto.TeleportResponse;
import com.teleport.searchengine.utils.TranTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ExcerciseApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@PropertySource("classpath:application-test.properties")
class ProducerApplicationTests {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void addOrderShouldBeSuccessful() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setCurrencyCode("NAIRA");
        orderDto.setDate("2021-12-20");
        orderDto.setAmount("2000.00");
        orderDto.setTransactionType(TranTypeEnum.SALE.name());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("00")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.respBody.date", Matchers.is("2021-12-20")))
                .andExpect(jsonPath("$.respBody.amount", Matchers.is(2000.00)))
                .andExpect(jsonPath("$.respBody.currencyCode", Matchers.is("NAIRA")))
                .andExpect(jsonPath("$.respBody.transactionType", Matchers.is(TranTypeEnum.SALE.name())));
    }


    //incomplete request body, amount missing
    @Test
    void addOrderShouldThrowBadRequest() throws Exception {

        String json = "{\n" +
                "  \"amount\": ,\n" +
                "  \"currencyCode\": \"NAIRA\",\n" +
                "  \"date\": \"12/20/2021\",\n" +
                "  \"transactionType\": \"1000\"\n" +
                "}";
        OrderDto orderDto = new OrderDto();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    void addOrderShouldThrow500() throws Exception {

        String json = "{\n" +
                "  \"amount\": \"1000\",\n" +
                "  \"currencyCode\": \"NAIRA\",\n" +
                "  \"date\": \"hello\",\n" +
                "  \"transactionType\": \"1000\"\n" +
                "}";
        OrderDto orderDto = new OrderDto();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getOrderShouldBeSuccessful() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setCurrencyCode("NAIRA");
        orderDto.setDate("2021-12-20");
        orderDto.setAmount("2000.00");
        orderDto.setTransactionType(TranTypeEnum.SALE.name());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("00")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.respBody.date", Matchers.is("2021-12-20")))
                .andExpect(jsonPath("$.respBody.amount", Matchers.is("2000.00")))
                .andExpect(jsonPath("$.respBody.currencyCode", Matchers.is("NAIRA")))
                .andExpect(jsonPath("$.respBody.transactionType", Matchers.is(TranTypeEnum.SALE.name())));
    }


    @Test
    void getOrderShouldThrow404() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/{id}", "190"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("404")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("NOT FOUND")));
    }

    @Test
    void lisOrderShouldThrowSuccess() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setCurrencyCode("NAIRA");
        orderDto.setDate("2021-12-20");
        orderDto.setAmount("2000.00");
        orderDto.setTransactionType(TranTypeEnum.SALE.name());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("00")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("SUCCESS")));
    }


    @Test
    void editOrderShouldBeSuccessful() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setCurrencyCode("NAIRA");
        orderDto.setDate("2021-12-20");
        orderDto.setAmount("2000.00");
        orderDto.setTransactionType(TranTypeEnum.SALE.name());
       MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto))).andReturn();

        String id = "";

        if(result != null){
            String json = result.getResponse().getContentAsString();

           var response =  objectMapper.readValue(json, TeleportResponse.class);

           log.info("This was recieved from add order {}",response);

            HashMap data =(HashMap) response.getRespBody();

            id =data.get("id")+"";
        }

        log.info("This is the id to be updated {}", id);

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setCurrencyCode("NAIRA");
        orderDto1.setDate("2021-12-21");
        orderDto1.setAmount("2000.00");
        orderDto1.setTransactionType(TranTypeEnum.SALE.name());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto1)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("00")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("SUCCESS")))
                .andExpect(jsonPath("$.respBody.date", Matchers.is("2021-12-21")))
                .andExpect(jsonPath("$.respBody.amount", Matchers.is("2000.00")))
                .andExpect(jsonPath("$.respBody.currencyCode", Matchers.is("NAIRA")))
                .andExpect(jsonPath("$.respBody.transactionType", Matchers.is(TranTypeEnum.SALE.name())));
    }

    @Test
    void editOrderShouldThrowBadRequest() throws Exception {

        String json = "{\n" +
                "  \"amount\": ,\n" +
                "  \"currencyCode\": \"NAIRA\",\n" +
                "  \"date\": \"12/20/2021\",\n" +
                "  \"transactionType\": \"1000\"\n" +
                "}";
        OrderDto orderDto = new OrderDto();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editOrderShouldThrow500() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setCurrencyCode("NAIRA");
        orderDto.setDate("2021-12-20");
        orderDto.setAmount("2000.00");
        orderDto.setTransactionType(TranTypeEnum.SALE.name());
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto))).andReturn();

        String id = "";

        if(result != null){
            String json = result.getResponse().getContentAsString();

            var response =  objectMapper.readValue(json, TeleportResponse.class);

            log.info("This was recieved from add order {}",response);

            HashMap data =(HashMap) response.getRespBody();

            id =data.get("id")+"";
        }

        String json = "{\n" +
                "  \"amount\": \"1000.00\",\n" +
                "  \"currencyCode\": \"NAIRA\",\n" +
                "  \"date\": \"hello\",\n" +
                "  \"transactionType\": \"1000\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void editOrderShouldThrow404() throws Exception {

        String json = "{\n" +
                "  \"amount\": \"1000.00\",\n" +
                "  \"currencyCode\": \"NAIRA\",\n" +
                "  \"date\": \"2022-12-20\",\n" +
                "  \"transactionType\": \"SALE\"\n" +
                "}";
        //OrderDto orderDto = new OrderDto();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/order/{id}", "100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }


    @Test
    void deleteOrderShouldBeSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/order/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.respCode", Matchers.is("00")))
                .andExpect(jsonPath("$.respDescription", Matchers.is("SUCCESS")));
    }

    @Test
    void contextLoads() {
    }
}