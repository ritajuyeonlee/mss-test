package com.mss.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mss.config.JpaAuditingConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@Import(JpaAuditingConfig.class)
@ActiveProfiles("h2")
@Transactional
@RecordApplicationEvents
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class MerchandiseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Nested
    @Order(1)
    @DisplayName("게스트매물 등록")
    class inner {

        @Test
        @DisplayName("상품생성 API 테스트")
        void createMerchandise() throws Exception {


        }


    }
}
