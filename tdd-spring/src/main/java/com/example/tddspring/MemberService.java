package com.example.tddspring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembershipRepository membershipRepository;
    public MembershipResponse addMembership(String userId, MembershipType membershipType, Integer point) {
        Membership findMembership = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if(findMembership != null){
            throw new MembershipException(MembershipErrorResult.DUPLICATE_MEMBERSHIP_REGISTER);
        }

        Membership membership = Membership.builder()
                .userId(userId)
                .membershipType(membershipType)
                .point(point)
                .build();


        Membership saveMembership = membershipRepository.save(membership);

        return MembershipResponse.builder()
                .id(saveMembership.getId())
                .membershipType(saveMembership.getMembershipType())
                .build();
    }
}
