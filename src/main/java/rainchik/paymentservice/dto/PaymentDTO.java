package rainchik.paymentservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {

    private Long orderId;

    private Long userId;

    @Positive
    private BigDecimal amount;

}
