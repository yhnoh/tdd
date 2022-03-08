package com.example.tddspring;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class MembershipException extends RuntimeException{

    private final MembershipErrorResult errorResult;
}
