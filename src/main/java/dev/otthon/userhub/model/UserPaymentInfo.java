package dev.otthon.userhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_payment_info")
public class UserPaymentInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "user_payment_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expiration_month")
    private Long cardExpirationMonth;

    @Column(name = "card_expiration_year")
    private Long cardExpirationYear;

    @Column(name = "card_security_code")
    private String cardSecurityCode;

    private BigDecimal price;

    @Column(name = "dt_payment")
    private LocalDate dtPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
