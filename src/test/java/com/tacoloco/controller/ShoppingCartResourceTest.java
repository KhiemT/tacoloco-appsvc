package com.tacoloco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tacoloco.controller.dto.ItemRequest;
import com.tacoloco.controller.dto.OrderRequest;
import com.tacoloco.controller.dto.OrderResponse;
import com.tacoloco.exception.ExceptionTranslator;
import com.tacoloco.exception.TranslatableToRestError;
import com.tacoloco.exception.unchecked.InvalidProductQuantityException;
import com.tacoloco.service.ShoppingCartService;
import com.tacoloco.service.localization.MessageByLocaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.MockMvcConfigurerAdapter;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartResourceTest {
    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    private static final String baseResourceUrl = "http://localhost:8081/api/v1/shopping-carts";
    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    public void setup() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();
    }

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get(baseResourceUrl + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Detroit Lab")));
    }

    @Test
    void shouldReturnTotalPriceDiscountNotAppliedForLess4SameProductInOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(new ItemRequest("VEGGIE", 3)));
        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(7.5));
    }

    @Test
    void shouldReturnTotalPriceDiscountAppliedForMore4SameProductInOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(new ItemRequest("VEGGIE", 4)));
        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(8));
    }

    @Test
    void shouldReturnTotalPriceDiscountAppliedForMore4DifferentProductInOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(
                new ItemRequest("BEEF", 100),
                new ItemRequest("CHICKEN", 200),
                new ItemRequest("CHORIZO", 500),
                new ItemRequest("VEGGIE", 600)
        ));
        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(3320));
    }

    @Test
    void shouldReturnProductInstanceNotFoundException() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(
                new ItemRequest("UNKNOWN", 100),
                new ItemRequest("SOMETHING ELSE", 200)
        ));

        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.extraMessage").value("product.instance.not.found"));

    }

    @Test
    void shouldReturnInvalidOrderRequestException() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(new ArrayList<>());

        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.extraMessage").value("product.order.request.invalid"));

    }
    @Test
    void shouldReturnInvalidProductQuantityException() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(
                new ItemRequest("BEEF", 0),
                new ItemRequest("CHICKEN", 200),
                new ItemRequest("CHORIZO", 500),
                new ItemRequest("VEGGIE", 600)));

        this.mockMvc.perform(post(baseResourceUrl + "/total-price")
                .content(asJsonString(orderRequest)).contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.extraMessage").value("product.invalid.quantity"));

    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
