package rainchik.paymentservice.unit;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.entity.Payment;
import rainchik.paymentservice.entity.Status;
import rainchik.paymentservice.mapper.PaymentMapper;
import rainchik.paymentservice.repository.PaymentRepository;
import rainchik.paymentservice.service.RandomService;
import rainchik.paymentservice.service.impl.PaymentServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private RandomService randomService;

    @Mock
    private KafkaTemplate kafkaTemplate;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void createPayment_ShouldSaveAndReturnPayment() {
        // Arrange
        PaymentDTO dto = new PaymentDTO();
        Payment payment = new Payment();
        Payment savedPayment = new Payment();
        PaymentResponseDTO response = new PaymentResponseDTO();

        when(paymentMapper.toPayment(dto)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(savedPayment);
        when(paymentMapper.toPaymentDTO(savedPayment)).thenReturn(response);
        when(randomService.processPayment()).thenReturn(Status.SUCCESS);
        when(kafkaTemplate.send((Message) any())).thenReturn(any());

        // Act
        paymentService.createPayment(dto);

        // Assert
        verify(paymentMapper).toPayment(dto);
        verify(paymentRepository).save(payment);
        verify(paymentMapper).toPaymentDTO(savedPayment);
    }

    @Test
    void getPaymentsByUserId_ShouldReturnPayments() {
        // Arrange
        Long userId = 1L;
        List<Payment> payments = List.of(new Payment());
        List<PaymentResponseDTO> expected = List.of(new PaymentResponseDTO());

        when(paymentRepository.findByUserId(userId)).thenReturn(payments);
        when(paymentMapper.toPaymentDTO(payments)).thenReturn(expected);

        // Act
        paymentService.getPaymentsByUserId(userId);

        // Assert
        verify(paymentRepository).findByUserId(userId);
        verify(paymentMapper).toPaymentDTO(payments);
    }

    @Test
    void getPaymentsByOrderId_ShouldReturnPayments() {
        // Arrange
        Long orderId = 100L;
        List<Payment> payments = List.of(new Payment());
        List<PaymentResponseDTO> expected = List.of(new PaymentResponseDTO());

        when(paymentRepository.findByOrderId(orderId)).thenReturn(payments);
        when(paymentMapper.toPaymentDTO(payments)).thenReturn(expected);

        // Act
        paymentService.getPaymentsByOrderId(orderId);

        // Assert
        verify(paymentRepository).findByOrderId(orderId);
        verify(paymentMapper).toPaymentDTO(payments);
    }

    @Test
    void getSumOfPaymentsBetweenDates_ShouldReturnSum() {
        // Arrange
        var start = java.time.LocalDateTime.now();
        var end = start.plusDays(1);
        BigDecimal expectedSum = BigDecimal.valueOf(100.50);

        when(paymentRepository.sumPaymentsBetweenDates(start, end)).thenReturn(expectedSum);

        // Act
        paymentService.getSumOfPaymentsBetweenDates(start, end);

        // Assert
        verify(paymentRepository).sumPaymentsBetweenDates(start, end);
    }
}
