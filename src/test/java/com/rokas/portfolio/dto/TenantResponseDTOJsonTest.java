package com.rokas.portfolio.dto;

import static org.mockito.Mockito.timeout;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureJsonTesters
@Transactional
public class TenantResponseDTOJsonTest {

    @Autowired
    private JacksonTester<TenantResponseDTO> json;

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    void TenantResponseDTOSerializationTest() throws IOException {
        Tenant tenant = new Tenant();
        tenant.setName("test_name");
        tenant.setTenantKey("test_key");

        Tenant saved = tenantRepository.save(tenant);

        TenantResponseDTO dto = new TenantResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getTenantKey(),
            saved.getCreatedAt()
        );
        
        var jsonOut = json.write(dto);

        assertThat(jsonOut).hasJsonPathNumberValue("$.id");
        assertThat(jsonOut).extractingJsonPathNumberValue("$.id").isNotNull();
        assertThat(jsonOut).extractingJsonPathStringValue("$.name").isEqualTo("test_name");
        assertThat(jsonOut).extractingJsonPathStringValue("$.tenantKey").isEqualTo("test_key");
        assertThat(jsonOut).extractingJsonPathStringValue("$.createdAt").isNotNull();
    }
    
}
