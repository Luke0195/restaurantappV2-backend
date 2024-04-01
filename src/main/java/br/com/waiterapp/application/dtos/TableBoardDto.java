package br.com.waiterapp.application.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableBoardDto implements Serializable {

    private UUID id;
    @NotEmpty(message="The field name must be required")
    private String name;
}
