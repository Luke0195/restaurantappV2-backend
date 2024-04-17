package br.com.waiterapp.application.dtos.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDto implements Serializable {


    @NotEmpty(message = "The field name must be required")
    private String name;
    @NotEmpty(message = "The field icon must be required")
    private String icon;
}
