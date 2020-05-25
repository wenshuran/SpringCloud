package ServiceConsumer;

public class ConsumerFallImpl implements ConsumerFeignService{

    @Override
    public User getUser(Long id) {
        System.out.println("Using Hystrix");
        return null;
    }
}
