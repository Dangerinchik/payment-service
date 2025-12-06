package rainchik.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rainchik.paymentservice.entity.Payment;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String>, PaymentRepositoryAdvanced {

    public Payment findByUserId(Long userId);
    public Payment findByOrderId(Long orderId);
    public Payment findByStatusIn(List<String> statuses);

}
