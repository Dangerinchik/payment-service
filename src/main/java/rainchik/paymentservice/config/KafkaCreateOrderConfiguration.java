package rainchik.paymentservice.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;
import org.springframework.kafka.support.mapping.JacksonJavaTypeMapper;
import tools.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JacksonJsonMessageConverter;
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;

@Configuration
public class KafkaCreateOrderConfiguration {

    @Bean
    JacksonJsonMessageConverter jacksonJsonMessageConverter(@Qualifier("consumer") JsonMapper jsonMapper) {
        JacksonJsonMessageConverter messageConverter = new StringJacksonJsonMessageConverter(jsonMapper);

        JacksonJavaTypeMapper typeMapper = new DefaultJacksonJavaTypeMapper();
        typeMapper.addTrustedPackages("rainchik.paymentservice.dto");
        typeMapper.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.TYPE_ID);

        messageConverter.setTypeMapper(typeMapper);

        return messageConverter;
    }

}
