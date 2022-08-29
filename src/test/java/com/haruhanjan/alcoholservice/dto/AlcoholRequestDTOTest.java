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
        AlcoholRequestDTO dto = AlcoholRequestDTO.builder()
                .acidDegree(1)
                .isSparkling(true)
                .alcoholByVolume(10.3)
                .alcoholType(AlcoholType.WINE)
                .seller("asdf")
                .sugarDegree(2)
                .madeFrom("한국")
                .name("와인")
                .price(12000)
                .productDate(LocalDate.of(2022,10,10))
                .expiryDate(LocalDate.of(2022,11,11))
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
        assertThat(result.getProductDate()).isEqualTo(productDate);

    }

}