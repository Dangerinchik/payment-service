package rainchik.paymentservice.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface PaymentRepositoryAdvanced {

    BigDecimal sumPaymentsBetweenDates(LocalDateTime start, LocalDateTime end);

}
