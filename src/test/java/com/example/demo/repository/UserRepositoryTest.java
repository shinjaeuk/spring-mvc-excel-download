package com.example.demo.repository;

import static org.junit.Assert.assertThat;

import com.example.demo.entity.User;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void select_user() {
        List<User> all = userRepository.findAll();
        assertThat(all.size() > 0, is(true));
    }
}