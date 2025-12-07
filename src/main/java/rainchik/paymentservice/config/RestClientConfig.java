package rainchik.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("external-api.random.url")
    private String externalRandomUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(externalRandomUrl).build();
    }

}
