package rainchik.paymentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rainchik.paymentservice.dto.PaymentDTO;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.entity.Payment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    public Payment toPayment(PaymentDTO paymentDTO);
    public PaymentResponseDTO toPaymentDTO(Payment payment);

}
