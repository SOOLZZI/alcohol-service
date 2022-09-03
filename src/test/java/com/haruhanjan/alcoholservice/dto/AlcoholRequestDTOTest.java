package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AlcoholRequestDTOTest {

    @Test
    @DisplayName("엔티티 변환이 잘 되는가")
    void toEntity() throws Exception {
        //given
        String name = "소주";
        Integer price = 2000;
        Double volume = 10.5;
        String madeFrom = "한국";
        String seller = "사용자1";
        AlcoholType alcoholType = AlcoholType.SPIRIT;
        LocalDate productDate = LocalDate.of(1972, 11, 20);
        Integer acidDegree = 1;
        boolean isSparkling = true;
        Integer sugarDegree = 2;

        AlcoholRequestDTO dto = AlcoholRequestDTO.builder()
                .acidDegree(acidDegree)
                .isSparkling(isSparkling)
                .alcoholByVolume(volume)
                .alcoholType(alcoholType)
                .seller(seller)
                .sugarDegree(sugarDegree)
                .madeFrom(madeFrom)
                .name(name)
                .price(price)
                .build();

        //when
        Alcohol result = dto.toEntity();

        //then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPrice()).isEqualTo(price);
        assertThat(result.getAlcoholByVolume()).isEqualTo(volume);
        assertThat(result.getMadeFrom()).isEqualTo(madeFrom);
        assertThat(result.getSeller()).isEqualTo(seller);
        assertThat(result.getAlcoholType()).isEqualTo(alcoholType);

    }

}