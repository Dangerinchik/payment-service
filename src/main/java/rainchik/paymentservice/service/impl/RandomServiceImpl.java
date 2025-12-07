package rainchik.paymentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import rainchik.paymentservice.entity.Status;
import rainchik.paymentservice.service.RandomService;

@Service
public class RandomServiceImpl implements RandomService {

    private static  String EXTERNAL_API_PARAMETRES = "?num=1&min=1&max=1000&col=1&base=10&format=plain&rnd=new";
    private final RestClient restClient;

    @Autowired
    public RandomServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Status processPayment() {
        Integer result = restClient.get().uri(EXTERNAL_API_PARAMETRES).retrieve().toEntity(Integer.class).getBody();
        if(result != null && result % 2 == 0){
            return Status.SUCCESS;
        }
        else{
            return Status.FAILED;
        }
    }
}
