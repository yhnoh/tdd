package com.example.tddspring;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MembershipType {
    NAVER("네이버"),
    LINE("다음"),
    KAKAO("카카오"),
    ;


    private final String companyName;
}
