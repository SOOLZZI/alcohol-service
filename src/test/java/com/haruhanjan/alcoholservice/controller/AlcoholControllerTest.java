package com.haruhanjan.alcoholservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.alcoholservice.apiDocs.RestDocTestConfiguration;
import com.haruhanjan.alcoholservice.dto.AlcoholRequestDTO;
import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import com.haruhanjan.alcoholservice.service.AlcoholService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(AlcoholController.class)
@Import(RestDocTestConfiguration.class)
@AutoConfigureRestDocs
class AlcoholControllerTest {

    @MockBean
    private AlcoholService alcoholService;
    @Autowired
    private MockMvc mockMvc; // http ??????

    AlcoholRequestDTO dto = AlcoholRequestDTO.builder()
            .name("??????")
            .price(1234)
            .madeFrom("??????")
            .alcoholType(AlcoholType.BEER)
            .seller("???")
            .alcoholByVolume(12.3)
            .storagePeriod(123)
            .acidDegree(2)
            .isSparkling(false)
            .sugarDegree(3)
            .build();
    Alcohol entity = Alcohol.builder()
            .id(1L)
            .name("??????")
            .price(1234)
            .alcoholType(AlcoholType.BEER)
            .seller("???")
            .alcoholByVolume(12.3)
            .madeFrom("??????")
            .storagePeriod(123)
            .acidDegree(2)
            .isSparkling(false)
            .sugarDegree(3)
            .build();

    AlcoholResponseDTO responseDTO = new AlcoholResponseDTO(entity);
    List<AlcoholResponseDTO> responseDTOList = new ArrayList<>();


    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void getAllAlcoholTest() throws Exception {

        for (int i = 0; i < 10; i++) responseDTOList.add(responseDTO);

        when(alcoholService.getAll(PageRequest.of(0, 3))).thenReturn((responseDTOList));

        ResultActions result = this.mockMvc.perform(get("/api/alcohol").param("page", "0").param("size", "3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("alcohol-get-all",
                        responseFields(fieldWithPath("[]").description("List of Alcohol")).andWithPrefix("[].", alcoholWithId)));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void getAlcoholTest() throws Exception {
        Long sampleId = responseDTO.getId();
        when(alcoholService.get(anyLong())).thenReturn((responseDTO));

        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/alcohol/{id}", sampleId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(document("alcohol-get-one",
                        pathParameters(
                                parameterWithName("id").description("????????? ID")
                        ),
                        responseFields(alcoholWithId)
                ));
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
    void saveTest() throws Exception {
        // given
        when(alcoholService.save(dto)).thenReturn(responseDTO);

        // when
        ResultActions result = mockMvc.perform(post("/api/alcohol")
                .content(toJson(dto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isCreated())
                .andDo(document("alcohol-post",
                        requestFields(alcoholWithoutId)));
        // HttpStatus CREATED??? Response Body??? ??????.
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
    void alcoholPutTest() throws Exception {
        // given
        Long sampleId = responseDTO.getId();

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/api/alcohol/{id}", sampleId)
                .content(toJson(dto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andDo(document("alcohol-put-by-id",
                        pathParameters(
                                parameterWithName("id").description("????????? ID")
                        ),
                        requestFields(alcoholWithoutId)
                ));
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
    void alcoholPatchTest() throws Exception {
        // given
        Long sampleId = responseDTO.getId();

        // when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/alcohol/{id}", sampleId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isNoContent())
                .andDo(document("alcohol-delete-by-id",
                        pathParameters(
                                parameterWithName("id").description("????????? ID")
                        )
                ));
    }

    FieldDescriptor[] alcoholWithoutId = new FieldDescriptor[]{
            fieldWithPath("name").description("??? ??????").type(JsonFieldType.STRING),
            fieldWithPath("price").description("??? ??????").type(JsonFieldType.NUMBER),
            fieldWithPath("alcoholByVolume").description("??? ??????").type(JsonFieldType.NUMBER),
            fieldWithPath("madeFrom").description("??? ?????? ?????????").type(JsonFieldType.STRING),
            fieldWithPath("alcoholType").description("??? ??????").type(JsonFieldType.STRING),
            fieldWithPath("seller").description("????????? ??????").type(JsonFieldType.STRING),
            fieldWithPath("storagePeriod").description("?????? ?????? ??????(?????? ???)").type(JsonFieldType.NUMBER),
            fieldWithPath("sugarDegree").description("??????").type(JsonFieldType.NUMBER),
            fieldWithPath("acidDegree").description("??????").type(JsonFieldType.NUMBER),
            fieldWithPath("isSparkling").description("?????? ?????? ??????").type(JsonFieldType.BOOLEAN)
    };
    FieldDescriptor[] alcoholWithId = new FieldDescriptor[]{
            fieldWithPath("id").description("??? ?????? ?????????").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("??? ??????").type(JsonFieldType.STRING),
            fieldWithPath("price").description("??? ??????").type(JsonFieldType.NUMBER),
            fieldWithPath("alcoholByVolume").description("??? ??????").type(JsonFieldType.NUMBER),
            fieldWithPath("madeFrom").description("??? ?????? ?????????").type(JsonFieldType.STRING),
            fieldWithPath("alcoholType").description("??? ??????").type(JsonFieldType.STRING),
            fieldWithPath("seller").description("????????? ??????").type(JsonFieldType.STRING),
            fieldWithPath("storagePeriod").description("?????? ?????? ??????(?????? ???)").type(JsonFieldType.NUMBER),
            fieldWithPath("sugarDegree").description("??????").type(JsonFieldType.NUMBER),
            fieldWithPath("acidDegree").description("??????").type(JsonFieldType.NUMBER),
            fieldWithPath("isSparkling").description("?????? ?????? ??????").type(JsonFieldType.BOOLEAN)
    };

    private <T> String toJson(T data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}