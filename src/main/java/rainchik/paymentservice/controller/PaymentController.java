package rainchik.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rainchik.paymentservice.dto.PaymentResponseDTO;
import rainchik.paymentservice.entity.Status;
import rainchik.paymentservice.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByUserId(@RequestParam("userId") Long userId) {

       List<PaymentResponseDTO> dtos =  paymentService.getPaymentsByUserId(userId);
       return ResponseEntity.ok().body(dtos);

    }

    @GetMapping("/order")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByOrderId(@RequestParam("orderId") Long orderId) {

        List<PaymentResponseDTO> dtos =  paymentService.getPaymentsByOrderId(orderId);
        return ResponseEntity.ok().body(dtos);

    }

    @GetMapping("/statuses")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByStatuses(@RequestParam("statuses") List<Status> statuses) {

        List<PaymentResponseDTO> dtos = paymentService.getPaymentsByStatuses(statuses);
        return ResponseEntity.ok().body(dtos);

    }

    @GetMapping("/sum")
    public ResponseEntity<BigDecimal> sum(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {

        BigDecimal sum = paymentService.getSumOfPaymentsBetweenDates(start, end);
        return ResponseEntity.ok().body(sum);

    }

}
