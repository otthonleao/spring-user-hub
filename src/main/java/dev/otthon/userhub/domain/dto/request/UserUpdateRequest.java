package dev.otthon.userhub.domain.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateRequest {

    @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 30 caracteres")
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @Pattern(regexp = "^[0-9]{1,11}$", message = "Número de telefone inválido. Somente números são permitidos e deve ter no máximo 11 dígitos.")
    private String phone;

    @Size(min = 11, max = 11, message = "O CPF deve conter 11 dígitos")
    @CPF(message = "CPF inválido. O CPF deve conter 11 dígitos. Envie apenas os números.")
    private String cpf;

    private Long userType;

}
