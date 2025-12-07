package rainchik.paymentservice.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class OrderKafkaDTO {

    private Long orderId;
    private Long userId;
    private BigDecimal amount;
}
