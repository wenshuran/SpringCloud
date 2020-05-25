package ServiceConsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
//    private static final String PROVIDER_URL = "http://localhost:8001";
    private static final String PROVIDER_URL = "http://PROVIDER";

//    @Resource
//    private RestTemplate restTemplate;
//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable("id") long id){
//        return restTemplate.getForObject(PROVIDER_URL + "" + id, User.class);
//    }


    @Resource
    private ConsumerFeignService consumerFeignService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id){
        return consumerFeignService.getUser(id);
    }
}
