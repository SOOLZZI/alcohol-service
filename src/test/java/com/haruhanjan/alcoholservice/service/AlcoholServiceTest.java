package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.dto.CreateAlcoholRequestDTO;
import com.haruhanjan.alcoholservice.dto.ModifyAlcoholRequestDTO;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import com.haruhanjan.alcoholservice.repository.AlcoholRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@Slf4j
class AlcoholServiceTest {

    @Mock
    private AlcoholRepository alcoholRepository;

    @InjectMocks
    private AlcoholService alcoholService;

    CreateAlcoholRequestDTO sample1 = new CreateAlcoholRequestDTO(
            "소주",
            10000,
            10.8,
            "한국",
            "asdf",
            AlcoholType.SPIRIT,
            LocalDate.of(2022, 10, 10)
    );

    Alcohol returnSample = Alcohol.builder()
            .id(1L)
            .name("소주")
            .price(10000)
            .alcoholType(AlcoholType.SPIRIT)
            .seller("asdf")
            .volume(10.8)
            .productDate(LocalDate.of(2022, 10, 10))
            .madeFrom("한국")
            .build();
    Alcohol modifiedSample = Alcohol.builder()
            .id(1L)
            .name("카스")
            .alcoholType(AlcoholType.BEER)
            .price(10000)
            .seller("asdf")
            .volume(10.8)
            .productDate(LocalDate.of(2022, 10, 10))
            .madeFrom("한국")
            .build();

    @Test
    @ExtendWith(SpringExtension.class)
    @DisplayName("alcohol 저장이 잘 수행되는가")
    void save() throws Exception {
        //given
        when(alcoholRepository.save(any())).thenReturn(returnSample);

        //when
        AlcoholResponseDTO result = alcoholService.save(sample1);

        //then
        log.info("saved ID :{}", result.getId());
        assertThat("소주").isEqualTo(result.getName());
        assertThat(10000).isEqualTo(result.getPrice());
        assertThat(10.8).isEqualTo(result.getVolume());
        assertThat("한국").isEqualTo(result.getMadeFrom());
        assertThat("asdf").isEqualTo(result.getSeller());
        assertThat(AlcoholType.SPIRIT.name()).isEqualTo(result.getAlcoholType());
        assertThat(LocalDate.of(2022, 10, 10)).isEqualTo(result.getProductDate());

    }

    @Test
    @ExtendWith(SpringExtension.class)
    @DisplayName("술 변경 테스트_O")
    void modifyTest_O() {
        // given
        when(alcoholRepository.findById(any())).thenReturn(Optional.of(modifiedSample));

        ModifyAlcoholRequestDTO modifyDto = new ModifyAlcoholRequestDTO();
        modifyDto.setAlcoholType(AlcoholType.BEER);
        modifyDto.setName("카스");

        // when
        alcoholService.modify(modifiedSample.getId(), modifyDto);
        Alcohol result = alcoholRepository.findById(modifiedSample.getId()).orElse(null);

        // then
        assertThat("카스").isEqualTo(result.getName());
        assertThat(AlcoholType.BEER.name()).isEqualTo(result.getAlcoholType().toString());
        assertThat(10000).isEqualTo(result.getPrice());
    }

}
