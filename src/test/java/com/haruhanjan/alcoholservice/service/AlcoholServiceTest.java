package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholRequestDTO;
import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
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
@ExtendWith(SpringExtension.class)
class AlcoholServiceTest {

    @Mock
    private AlcoholRepository alcoholRepository;

    @InjectMocks
    private AlcoholService alcoholService;
    private AlcoholRequestDTO getRequestSample() {
        return AlcoholRequestDTO.builder()
                .acidDegree(1)
                .isSparkling(true)
                .alcoholByVolume(10.3)
                .alcoholType(AlcoholType.WINE)
                .seller("asdf")
                .sugarDegree(2)
                .madeFrom("한국")
                .name("와인")
                .price(12000)
                .build();
    }

    Alcohol returnSample = Alcohol.builder()
            .id(1L)
            .name("소주")
            .price(10000)
            .alcoholType(AlcoholType.SPIRIT)
            .seller("asdf")
            .alcoholByVolume(10.8)
            .madeFrom("한국")
            .build();
    Alcohol modifiedSample = Alcohol.builder()
            .id(1L)
            .acidDegree(1)
            .isSparkling(true)
            .alcoholByVolume(10.3)
            .alcoholType(AlcoholType.BEER)
            .seller("asdf")
            .sugarDegree(2)
            .madeFrom("한국")
            .name("카스")
            .price(10000)
            .build();

    @Test
    @DisplayName("alcohol 저장이 잘 수행되는가")
    void save() throws Exception {
        //given
        when(alcoholRepository.save(any())).thenReturn(returnSample);

        //when
        AlcoholResponseDTO result = alcoholService.save(getRequestSample());

        //then
        log.info("saved ID :{}", result.getId());
        assertThat("소주").isEqualTo(result.getName());
        assertThat(10000).isEqualTo(result.getPrice());
        assertThat(10.8).isEqualTo(result.getAlcoholByVolume());
        assertThat("한국").isEqualTo(result.getMadeFrom());
        assertThat("asdf").isEqualTo(result.getSeller());
        assertThat(AlcoholType.SPIRIT.name()).isEqualTo(result.getAlcoholType());
        assertThat(LocalDate.of(2022, 10, 10)).isEqualTo(result.getProductDate());

    }

    @Test
    @DisplayName("술 변경 테스트_O")
    void modifyTest_O() {
        // given
        when(alcoholRepository.findById(any())).thenReturn(Optional.of(modifiedSample));

        AlcoholRequestDTO modifyDto = getRequestSample();

        modifyDto.setAlcoholType(AlcoholType.BEER);
        modifyDto.setName("카스");
        modifyDto.setPrice(10000);

        // when
        alcoholService.modify(modifiedSample.getId(), modifyDto);
        Alcohol result = alcoholRepository.findById(modifiedSample.getId()).orElse(null);

        // then
        assertThat(result.getName()).isEqualTo("카스");
        assertThat(AlcoholType.BEER.name()).isEqualTo(result.getAlcoholType().toString());
        assertThat(result.getPrice()).isEqualTo(10000);
    }

}
