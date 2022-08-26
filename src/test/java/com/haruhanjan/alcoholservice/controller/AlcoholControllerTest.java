package com.haruhanjan.alcoholservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.dto.CreateAlcoholRequestDTO;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import com.haruhanjan.alcoholservice.service.AlcoholService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@ExtendWith(SpringExtension.class)
class AlcoholControllerTest {

    @Mock
    private AlcoholService alcoholService;

    @InjectMocks
    private AlcoholController alcoholController;

    private MockMvc mockMvc; // http 호출
    CreateAlcoholRequestDTO dto = CreateAlcoholRequestDTO.builder()
            .name("카스")
            .price(1234)
            .madeFrom("한국")
            .alcoholType(AlcoholType.BEER)
            .seller("나")
            .alcoholByVolume(12.3)
            .build();
    Alcohol entity = Alcohol.builder()
            .id(1L)
            .name("카스")
            .price(1234)
            .alcoholType(AlcoholType.BEER)
            .seller("나")
            .alcoholByVolume(12.3)
            .productDate(LocalDate.of(2022, 10, 10))
            .madeFrom("한국")
            .build();

    AlcoholResponseDTO responseDTO = new AlcoholResponseDTO(entity);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(alcoholController)
                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
                .build();
    }

    @Test
    @DisplayName("알코올 저장 테스트_O")
    void saveTest_O() throws Exception {
        // given
        when(alcoholService.save(dto)).thenReturn(responseDTO);
        log.debug("dto toJson() : " + toJson(dto));

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/api/alcohol").content(toJson(dto)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        log.debug(response.getErrorMessage());

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private <T> String toJson(T data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}