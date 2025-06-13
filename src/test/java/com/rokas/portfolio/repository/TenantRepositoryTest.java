package com.rokas.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rokas.portfolio.config.TenantContext;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;
import com.rokas.portfolio.service.TenantService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    private TenantService tenantService;

    public Tenant createTestTenant() {
        Tenant tenant = new Tenant();
        tenant.setName("Test Corp");
        tenant.setTenantKey("atrandi");
        
        return tenant;
    }

    @BeforeEach
    void setUp() {
        tenantService = new TenantService(tenantRepository);
    }

    @Test
    void shouldSaveTenantToDB() {
        Tenant tenant = createTestTenant();
        Tenant saved = tenantRepository.save(tenant);

        assertNotNull(saved.getId());
        assertEquals("Test Corp", saved.getName());
        assertNotNull(saved.getCreatedAt());

        Tenant found = tenantRepository.findById(saved.getId()).orElseThrow();
        assertEquals("Test Corp", found.getName());
    }

    @Test
    void shouldReturnTenantFromContext() {
        TenantContext.setTenantId("atrandi");
        Tenant savedTenant = tenantRepository.save(createTestTenant());
        Tenant tenant = tenantService.getCurrentTenant();
    
        assertEquals(savedTenant.getId(), tenant.getId());
        assertEquals("atrandi", tenant.getTenantKey());

        TenantContext.clear();
    }
}