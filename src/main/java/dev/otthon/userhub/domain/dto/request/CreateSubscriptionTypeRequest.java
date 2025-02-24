package dev.otthon.userhub.domain.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateSubscriptionTypeRequest {

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 30, message = "O nome do produto deve ter entre 3 e 30 caracteres")
    private String name;

    @Max(value = 12, message = "O período de acesso deve ser de no máximo 12")
    private Long accessMonth;

    @NotNull(message = "Campo obrigatório, não pode ser nulo")
    @Positive(message = "O preço do produto deve ser um valor positivo")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior ou igual a 0.01")
    @DecimalMax(value = "10000.00", message = "O preço não pode ser maior que 10.000")
    @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal price;

    @NotBlank(message = "Campo obrigatório! Não pode ser nulo ou vazio")
    @Size(min = 5, max = 15, message = "O campo deve ter entre 5 e 15 caracteres")
    private String productKey;

}
