package com.rokas.portfolio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;

@ExtendWith(MockitoExtension.class)
public class TenantServiceTest {
    @Mock
    private TenantRepository tenantRepository;

    @InjectMocks
    private TenantService tenantService;

    @Test
    public void shouldCreateTenant() {
        TenantRequestDTO request = new TenantRequestDTO("test_name",
                                                        "tenant_key");
        
        Tenant tenant = new Tenant();
        tenant.setName(request.getName());
        tenant.setTenantKey(request.getTenantKey());
        
        when(tenantRepository.save(any(Tenant.class))).thenReturn(tenant);

        TenantResponseDTO result = tenantService.createTenant(request);

        assertThat(result.name()).isEqualTo("test_name");
        assertThat(result.tenantKey()).isEqualTo("tenant_key");
        assertThat(result.createdAt()).isNotNull();

        verify(tenantRepository).save(any(Tenant.class));
    }


    
}
