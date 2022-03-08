package com.example.tddspring;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {
    private int point;
    private MembershipType membershipType;
}
