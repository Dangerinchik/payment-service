package rainchik.paymentservice.service.impl;

import org.apache.kafka.common.metrics.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.entity.Payment;
import rainchik.paymentservice.entity.Status;
import rainchik.paymentservice.mapper.PaymentMapper;
import rainchik.paymentservice.repository.PaymentRepository;
import rainchik.paymentservice.service.PaymentService;
import rainchik.paymentservice.service.RandomService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RandomService randomService;
    private final PaymentMapper paymentMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, RandomService randomService, PaymentMapper paymentMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.randomService = randomService;
        this.paymentMapper = paymentMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(PaymentDTO dto) {
        Payment payment = paymentMapper.toPayment(dto);
        payment.setStatus(randomService.processPayment().name());
        PaymentResponseDTO response = paymentMapper.toPaymentDTO(paymentRepository.save(payment));
        Message<PaymentResponseDTO> message = MessageBuilder.withPayload(response).build();
        kafkaTemplate.send(message);
        return response;
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(Long userId) {
        return paymentMapper.toPaymentDTO(paymentRepository.findByUserId(userId));
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByOrderId(Long orderId) {
        return paymentMapper.toPaymentDTO(paymentRepository.findByOrderId(orderId));
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStatuses(List<Status> statuses) {
        return paymentMapper.toPaymentDTO(paymentRepository.findByStatusIn(
                statuses
                        .stream()
                        .map(status -> status.name())
                        .toList())
        );
    }

    @Override
    public BigDecimal getSumOfPaymentsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return paymentRepository.sumPaymentsBetweenDates(start, end);
    }
}
