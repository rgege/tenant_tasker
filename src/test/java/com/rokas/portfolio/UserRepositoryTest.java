package com.rokas.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.Role;
import com.rokas.portfolio.repository.TenantRepository;
import com.rokas.portfolio.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired 
    UserRepository userRepository;

    @Autowired
    TenantRepository tenantRepository;

    private User createTestUser() {
        User user = new User();
        user.setName("test_user");
        user.setEmail("test_email@test.com");
        user.setPassword("12345");
        user.setRole(Role.USER);
        user.setStatus(true);
        return user;
    }

    private Tenant createTestTenant() {
        Tenant tenant = new Tenant();
        tenant.setName("test_tenant");
        return tenant;
    }

    @Test
    void shouldSaveUserToDB() {
        User savedUser = userRepository.save(createTestUser());

        assertNotNull(savedUser.getId());
        assertEquals("test_user", savedUser.getName());
        assertEquals("test_email@test.com", savedUser.getEmail());
        assertTrue(savedUser.getStatus());
        assertNotNull(savedUser.getCreatedAt());

        User found = userRepository.findById(savedUser.getId()).orElseThrow();
        assertEquals("test_user", found.getName());
    }

    @Test
    void userShouldHaveTenantId() {
        Tenant tenantSaved = tenantRepository.save(createTestTenant());
       
        User user = createTestUser();
        user.setTenant(tenantSaved);
        User savedUser = userRepository.save(user);

        User found = userRepository.findById(savedUser.getId()).orElseThrow();
        assertNotNull(found.getTenant());
        assertEquals(tenantSaved.getId(), found.getTenant().getId());
        
    }
}
