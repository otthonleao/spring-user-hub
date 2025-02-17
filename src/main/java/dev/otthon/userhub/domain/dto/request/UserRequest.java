package dev.otthon.userhub.domain.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {

    @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 30 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "Campo obrigatório")
    private String email;

    @Pattern(regexp = "^[0-9]{1,11}$", message = "Número de telefone inválido. Somente números são permitidos e deve ter no máximo 11 dígitos.")
    private String phone;

    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
    @CPF(message = "CPF inválido. O CPF deve conter 11 dígitos. Envie apenas os números.")
    private String cpf;

    @NotNull(message = "Data de inscrição é obrigatória")
    @PastOrPresent(message = "A data de inscrição não pode ser futura")
    private LocalDate dtSubscription = LocalDate.now();

    @NotNull(message = "Data de expiração é obrigatória")
    @FutureOrPresent(message = "A data de expiração deve ser no futuro ou hoje")
    private LocalDate dtExpiration = LocalDate.now();

    @NotNull(message = "Tipo de usuário é obrigatório")
    private Long userType;

}
