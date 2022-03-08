package com.example.tddspring;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 컨트롤러 테스트
 * 컨트롤러는 함수 호출이 아닌 API 호출을 통해서 요청을 받고 응답을 처리
 * 메시지 컨버팅과 같은 작업이 필요
 * MocMvc 클래스 사용
 */
@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController target;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    public void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }
    @Test
    public void mockMvc가null이아님() throws Exception{


        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    public void 멤버십등록실패_사용자식별값이헤더에없음() throws Exception{
        //given
        String url = "/api/v1/membership";

        //when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                .contentType(MediaType.APPLICATION_JSON)
        );

        perform.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void 멤버십등록실패_포인트null() throws Exception{
        //given
        final String url = "/api/v1/membership";

        //when

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .header(MembershipConstants.USER_ID_HEADER, "12345")
                        .content(gson.toJson(membershipRequest(10000, MembershipType.NAVER)))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        
        perform.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    private MembershipRequest membershipRequest(int point, MembershipType membershipType) {
        return MembershipRequest.builder()
                .point(point)
                .membershipType(membershipType)
                .build();
    }


}
