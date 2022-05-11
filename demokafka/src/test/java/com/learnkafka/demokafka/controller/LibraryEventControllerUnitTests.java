package com.learnkafka.demokafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.demokafka.controler.LibraryEventsController;
import com.learnkafka.demokafka.domain.Book;
import com.learnkafka.demokafka.domain.LibraryEvent;
import com.learnkafka.demokafka.producer.LibraryEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTests {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    LibraryEventProducer libraryEventProducer;

    @Test
    void postLibraryEvent() throws Exception {
        //given
        Book book = Book.builder().bookId(123).bookAuthor("Indranil").bookName("Kafka with SpringBoot").build();
        LibraryEvent libraryEvent = LibraryEvent.builder().libraryEventId(null).book(book).build();
        String json = objectMapper.writeValueAsString(libraryEvent);
        doNothing().when(libraryEventProducer).sendLibraryEvent_Approach2(isA(LibraryEvent.class));

        //when
        mockMvc.perform(post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

        //then
    }

    @Test
    void postLibraryEvent_4xx() throws Exception {
        //given
        Book book = Book.builder().bookId(123).bookAuthor("Indranil").bookName("Kafka with SpringBoot").build();
        LibraryEvent libraryEvent = LibraryEvent.builder().libraryEventId(null).book(book).build();
        String json = objectMapper.writeValueAsString(libraryEvent);
        doNothing().when(libraryEventProducer).sendLibraryEvent_Approach2(isA(LibraryEvent.class));

        //when
        mockMvc.perform(post("/v1/libraryevent").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        //then
    }
}
