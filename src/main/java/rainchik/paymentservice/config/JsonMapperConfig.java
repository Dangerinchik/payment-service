package rainchik.paymentservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class JsonMapperConfig {

    @Bean
    @Qualifier("producer")
    public JsonMapper producerJsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        //jsonMapper.isEnabled(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS); в этом проект возможно не пригодится, но в общем вещь полезная
        return jsonMapper;
    }

    @Bean
    @Qualifier("consumer")
    public JsonMapper consumerJsonMapper() {
        JsonMapper jsonMapper = new JsonMapper();

        return jsonMapper;
    }

}
