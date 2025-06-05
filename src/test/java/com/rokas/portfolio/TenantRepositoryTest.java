package com.rokas.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    void shouldSaveTenantToDB() {
        Tenant tenant = new Tenant();
        tenant.setName("Test Corp");
        tenant.setIdentifier("test_id");

        Tenant saved = tenantRepository.save(tenant);

        assertNotNull(saved.getId());
        assertEquals("Test Corp", saved.getName());
        assertEquals(1, saved.getId());
        assertNotNull(saved.getCreatedAt());

        Optional<Tenant> found = tenantRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Corp", found.get().getName());
    }
}