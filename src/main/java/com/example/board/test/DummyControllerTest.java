package com.example.board.test;

import com.example.board.model.User;
import com.example.board.model.UserRole;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyControllerTest {

    @Autowired//DI
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        user.setRole(UserRole.ADMIN);
        userRepository.save(user);
        return "Success Sign-In";
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지에 2건씩 볼 수 있도록
    @GetMapping("/dummy/user")
    public List<User> pagetList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    //lamda expr
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("User not found : " + id);
        });
        return user;
    }

    //save함수는 id를 전달하지 않으면 insert를 해줌
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert
    //email, password
    @Transactional//함수 종료 시, autocommit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User requestUser) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Failed to Find Id : " + id);
        });//영속화 시점

        user.setEmail(requestUser.getEmail());
        user.setPassword(requestUser.getPassword());

        //dirty checking
        return null;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "Failed to delete : " + id;
        }
        return "Success to delete : " + id;
    }
}
