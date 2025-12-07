package rainchik.paymentservice.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import rainchik.paymentservice.repository.PaymentRepositoryAdvanced;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.aggregation.*;

@Repository
public class PaymentRepositoryAdvancedImpl implements PaymentRepositoryAdvanced {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PaymentRepositoryAdvancedImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public BigDecimal sumPaymentsBetweenDates(LocalDateTime start, LocalDateTime end) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("timestamp").gte(start).and("timestamp").lte(end)),
                Aggregation.group().sum("payment_amount").as("total")
        );

        AggregationResults<TotalSum> results = mongoTemplate.aggregate(agg, "payments", TotalSum.class);
        return results.getUniqueMappedResult() != null ? results.getUniqueMappedResult().total : BigDecimal.ZERO;
    }

    public static class TotalSum {
        private BigDecimal total;
        public BigDecimal getTotal() {
            return total;
        }
    }
}
