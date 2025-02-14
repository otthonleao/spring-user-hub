package dev.otthon.userhub.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.otthon.userhub.domain.model.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private LocalDate dtSubscription = LocalDate.now();
    private LocalDate dtExpiration;
    private UserTypeDTO userType;
    private SubscriptionType subscriptionType;

}
