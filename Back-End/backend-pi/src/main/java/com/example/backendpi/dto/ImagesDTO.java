package com.example.backendpi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagesDTO {
    private Long id;
    private List<String> url;
//    private Long cancha_id;
//    private Long categoria_id;
}
