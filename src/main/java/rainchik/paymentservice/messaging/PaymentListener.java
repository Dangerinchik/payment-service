package rainchik.paymentservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rainchik.paymentservice.dto.OrderKafkaDTO;

import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.service.PaymentService;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = {"create-order-topic"})
public class PaymentListener {

    private final PaymentService paymentService;

    @KafkaHandler
    public void listen(OrderKafkaDTO dto) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(dto.getOrderId());
        paymentDTO.setAmount(dto.getAmount());
        paymentDTO.setUserId(dto.getUserId());
        paymentService.createPayment(paymentDTO);
    }

}
