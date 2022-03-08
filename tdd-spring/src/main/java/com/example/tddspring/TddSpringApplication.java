package com.example.tddspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


@SpringBootApplication
public class TddSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddSpringApplication.class, args);
    }

}
