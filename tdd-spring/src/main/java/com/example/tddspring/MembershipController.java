package com.example.tddspring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MembershipController {

    @PostMapping("/api/v1/membership")
    public ResponseEntity<MembershipResponse> addMembership(
            @RequestHeader(MembershipConstants.USER_ID_HEADER) final String userId,
            @RequestBody final MembershipRequest membershipRequest){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
