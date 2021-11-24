package com.zekerijah.eventdemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zekerijah.eventdemo.controller.mapper.EventMapper;
import com.zekerijah.eventdemo.controller.mapper.PeriodMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@MockBeans(value = {
        @MockBean(value = EventMapper.class),
        @MockBean(value = PeriodMapper.class)
})
public class WebLayerBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired protected ObjectMapper objectMapper;

    @SneakyThrows
    protected MockHttpServletResponse doGet(String url) {
        return mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

    }

    @SneakyThrows
    protected <T> MockHttpServletResponse doPost(String url, T body) {
        return mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andReturn()
                .getResponse();

    }

    @SneakyThrows
    protected <T> MockHttpServletResponse doPut(String url, T body) {
        return mockMvc.perform(
                put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andReturn()
                .getResponse();
    }

    @SneakyThrows
    protected MockHttpServletResponse doDelete(String url) {
        return mockMvc.perform(
                delete(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }

}
