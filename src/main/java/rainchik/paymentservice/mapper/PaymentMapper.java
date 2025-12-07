package rainchik.paymentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.entity.Payment;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    public Payment toPayment(PaymentDTO paymentDTO);
    public PaymentResponseDTO toPaymentDTO(Payment payment);
    public List<PaymentResponseDTO> toPaymentDTO(List<Payment> payments);

}