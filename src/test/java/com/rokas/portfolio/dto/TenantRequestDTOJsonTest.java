package com.rokas.portfolio.dto;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import com.rokas.portfolio.dto.TenantRequestDTO;

@JsonTest
public class TenantRequestDTOJsonTest {

    @Autowired
    private JacksonTester<TenantRequestDTO> json;

    private String tenantRequestJsonInput = """
        {
            "name": "test_name",
            "tenantKey": "test_tenant_key"
        }\n""";

    @Test
    void TenantRequestDTOSerializationTest() throws IOException {
        TenantRequestDTO dto = new TenantRequestDTO(
            "test_name", 
            "test_tenant_key");

        assertThat(json.write(dto)).isStrictlyEqualToJson(tenantRequestJsonInput);
    }

    @Test
    void TenantRequestDTODeserializationTest() throws IOException {
        TenantRequestDTO dto = json.parseObject(tenantRequestJsonInput);
        assertThat(dto.getName()).isEqualTo("test_name");
        assertThat(dto.getTenantKey()).isEqualTo("test_tenant_key");
    }

    @Test
    void TenantRequestDTORoundTripTest() throws IOException {
        TenantRequestDTO original = new TenantRequestDTO(
            "test_name", 
            "test_tenant_key");

        String jsonOut = json.write(original).getJson();
        TenantRequestDTO deserialized = json.parseObject(jsonOut);

        assertThat(deserialized).isEqualTo(original);
    }
}
