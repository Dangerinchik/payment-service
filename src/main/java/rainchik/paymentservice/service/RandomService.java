package rainchik.paymentservice.service;

import org.springframework.stereotype.Service;
import rainchik.paymentservice.entity.Status;

@Service
public interface RandomService {

    public Status processPayment();

}
