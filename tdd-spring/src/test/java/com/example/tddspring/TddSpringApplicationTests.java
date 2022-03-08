package com.example.tddspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * [요구사항 확인]
 * 나의 멤버십 등록 API
 * 기능 : 나의 멤버십을 등록합니다.
 * 요청 : 사용자 식별값, 멤버십 이름, 포인트
 * 응답 : 멤버십 ID, 멤버십 이름
 *
 * 기능 개발 순서
 * repository -> service -> controller
 */

@SpringBootTest
class TddSpringApplicationTests {
    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void contextLoads() {
    }

}
