package rainchik.paymentservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JacksonJsonMessageConverter;
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;
import tools.jackson.databind.json.JsonMapper;


@Configuration
public class KafkaCreatePaymentTemplateConfig {

    @Bean
    JacksonJsonMessageConverter jsonMessageConverter(JsonMapper jsonMapper) {

        return new StringJacksonJsonMessageConverter(jsonMapper);

    }
}
