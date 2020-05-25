package ServiceConsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PROVIDER", fallback = ConsumerFallImpl.class)
public interface ConsumerFeignService {
    @GetMapping("/provider/user/{id}")
    User getUser(@PathVariable(value = "id") Long id);
}
