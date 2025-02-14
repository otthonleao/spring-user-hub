package dev.otthon.userhub.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserPaymentInfoDTO {

    private Long id;
    private String cardNumber;
    private Long cardExpirationMonth;
    private Long cardExpirationYear;
    private String cardSecurityCode;
    private BigDecimal price;
    private LocalDate dtPayment;
    private UserDTO user;

}
