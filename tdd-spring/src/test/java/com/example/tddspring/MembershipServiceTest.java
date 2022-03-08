package com.example.tddspring;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * 사용자 ID와 멤버십 타입으로 이미 멤버십이 존재하여 실패하는 테스트 코드작성
 *
 * @InjectMocks : 의존성이 주입되는 대상에 사용
 * @Inject : 가짜 객체 생성을 도와준다.
 */

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;

    //의존성이 주입되는 대상
    @InjectMocks
    private MemberService target;
    @Mock
    private MembershipRepository membershipRepository;

    @Test
    public void 멤버십등록실패_이미존재함(){
        //행위를 했을때에 대한 리턴 방식을 지정한다.
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        assertThatThrownBy(() -> target.addMembership(userId, membershipType, point)).isInstanceOf(MembershipException.class);

    }

    @Test
    public void 멤버십등록성공(){
        //given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(membership()).when(membershipRepository).save(any(Membership.class));

        //when
        final MembershipResponse result = target.addMembership(userId, membershipType, point);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);

        //vertify
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Membership.class));


    }

    private Membership membership(){
        return Membership.builder()
                .id(-1l)
                .userId(userId)
                .point(point)
                .membershipType(MembershipType.NAVER)
                .build();
    }
}
