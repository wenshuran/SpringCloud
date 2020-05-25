package ServiceProvider;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    private EurekaClient eurekaClient;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}") //user/1
    public User getUser(@PathVariable("id") long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return user;
        }
        else
            return null;
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        return applications;
    }


    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @PostMapping("/add")
    public void addUser(String name, String username, String password){
        User user = new User();
        user.setName("abc");
        user.setUsername("username");
        user.setPassword("password");
        userRepository.save(user);
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }


}