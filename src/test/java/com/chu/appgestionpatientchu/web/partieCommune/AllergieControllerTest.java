package com.chu.appgestionpatientchu.web.partieCommune;

import com.chu.appgestionpatientchu.dto.AllergieDto;
import com.chu.appgestionpatientchu.services.partieCommune.AllergieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AllergieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private AllergieService allergieService;

    @Test
    void addListOfAllergiesSuccessfully() throws Exception {
        //Given
        List<AllergieDto> allergieDtoList = new ArrayList<>();
        allergieDtoList.add(
                AllergieDto.builder()
                        .nomAllergie("A1")
                        .build()
        );
        allergieDtoList.add(
                AllergieDto.builder()
                        .nomAllergie("A2")
                        .build()
        );

        //When
        when(allergieService.saveAll(allergieDtoList)).thenReturn(
                    allergieDtoList.stream().map(allergieDto -> {
                        allergieDto.setId((long) Math.random()*100);
                        allergieDto.setAddedAt(new Date());
                        return allergieDto;
                    }).toList()
        );

        //Then
        mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/v1/categories/partieCommune/allergies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(allergieDtoList)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(allergieService, times(1)).saveAll(allergieDtoList);
    }
}
