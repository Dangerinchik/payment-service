package rainchik.paymentservice.service;

import org.springframework.stereotype.Service;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PaymentService {

    public PaymentResponseDTO createPayment(PaymentDTO dto);
    public List<PaymentResponseDTO> getPaymentsByUserId(Long userId);
    public List<PaymentResponseDTO> getPaymentsByOrderId(Long orderId);
    public List<PaymentResponseDTO> getPaymentsByStatuses(List<Status> statuses);
    public BigDecimal getSumOfPaymentsBetweenDates(LocalDateTime start, LocalDateTime end);


}
