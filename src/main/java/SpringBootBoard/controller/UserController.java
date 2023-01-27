package SpringBootBoard.controller;

import SpringBootBoard.exception.UserNotFoundException;
import SpringBootBoard.model.User;
import SpringBootBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users/add")
    public User AddUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User GetUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User UpdateUser(@RequestBody User user,@PathVariable Long id) {
        return userRepository.findById(id)
                .map(newUser -> {
                    newUser.setName(user.getName());
                    newUser.setGrade(user.getGrade());
                    newUser.setAge(user.getAge());
                    newUser.setEmail(user.getEmail());
                    return userRepository.save(newUser);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    public String DeleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        return String.format("아이디 {}번이 삭제되었습니다!",id);
    }
}
