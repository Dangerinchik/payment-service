package rainchik.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rainchik.paymentservice.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String>, PaymentRepositoryAdvanced {

    public List<Payment> findByUserId(Long userId);
    public List<Payment> findByOrderId(Long orderId);
    public List<Payment> findByStatusIn(List<String> statuses);

}
