//package com.musinsa.integration;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//@SpringBootTest
//public class MerchandiseControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Nested
//    @Order(1)
//    @DisplayName("게스트매물 등록")
//    class inner {
//
//        @Test
//        @DisplayName("상품생성 API 테스트")
//        void createMerchandise() throws Exception {
//            ConstrainedFields fields = new ConstrainedFields(AuthSignUpRequestDTO.class);
//
//            // 화이트 리스트에 없는 케이스
//            AuthSignUpRequestDTO failRequest = AuthSignUpRequestDTO.builder()
//                    .userId("apple")
//                    .password("fedaykin12#")
//                    .name("제갈량")
//                    .regNo("123456-1234567")
//                    .build();
//
//            this.mockMvc.perform(post("/szs/signup")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(this.objectMapper.writeValueAsString(failRequest)))
//                    .andExpect(status().isBadRequest())
//                    .andDo(document("화이트_리스트에_없음", resource(ResourceSnippetParameters.builder().tag(this.tag).build())));
//
//            // 성공 케이스
//            AuthSignUpRequestDTO successRequest = AuthSignUpRequestDTO.builder()
//                    .userId("fedaykin")
//                    .password("fedaykin12#")
//                    .name("관우")
//                    .regNo("681108-1582816")
//                    .build();
//
//            this.mockMvc.perform(post("/szs/signup")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(successRequest)))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$").value(true))
//                    .andDo(document("회원가입_성공",
//                            resource(
//                                    ResourceSnippetParameters.builder()
//                                            .description("화이트 리스트에 등록된 회원만 회원가입을 할 수 있다.")
//                                            .summary("회원가입 API")
//                                            .tag(this.tag)
//                                            .requestFields(
//                                                    fields.withPath("userId").description("사용자 아이디"),
//                                                    fields.withPath("password").description("비밀번호"),
//                                                    fields.withPath("name").description("이름"),
//                                                    fields.withPath("regNo").description("주민등록번호")
//                                            )
//                                            .requestSchema(Schema.schema("AuthSignUpRequestDTO"))
//                                            .build()
//                            )
//                    ));
//
//            this.mockMvc.perform(post("/szs/signup")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(successRequest)))
//                    .andExpect(status().isBadRequest())
//                    .andDo(document("아이디_중복", resource(ResourceSnippetParameters.builder().tag(this.tag).build())));
//        }
//
//
//    }
