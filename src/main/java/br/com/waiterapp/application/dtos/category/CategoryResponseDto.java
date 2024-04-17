package br.com.waiterapp.application.dtos.category;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto implements Serializable {

    private UUID id;
    private String name;
    private String icon;
    @JsonFormat(pattern = "yyyy-mm-dd hh:MM:ss")
    private String createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd hh:MM:ss")
    private String updatedAt;
}
