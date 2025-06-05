package com.rokas.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.Role;
import com.rokas.portfolio.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void shouldSaveUserToDB() {
        User user = new User();
        user.setName("test_user");
        user.setEmail("test_email@test.com");
        user.setPassword("12345");
        user.setRole(Role.USER);
        user.setStatus(true);

        User saved = userRepository.save(user);

        assertNotNull(saved.getId());
        assertEquals("test_user", saved.getName());
        assertEquals("test_email@test.com", saved.getEmail());
        assertEquals(true, saved.getStatus());
        assertEquals(1, saved.getId());
        assertNotNull(saved.getCreatedAt());

        Optional<User> found = userRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("test_user", found.get().getName());
    }
}
