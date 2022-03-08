package com.example.tddspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


/**
 * https://mangkyu.tistory.com/184
 * https://mangkyu.tistory.com/143
 * [요구사항 확인]
 * 나의 멤버십 등록 API
 * 기능 : 나의 멤버십을 등록합니다.
 * 요청 : 사용자 식별값, 멤버십 이름, 포인트
 * 응답 : 멤버십 ID, 멤버십 이름
 *
 * 기능 개발 순서
 * repository -> service -> controller
 *
 * TDD로 개발하면 이러한 클래스들은 테스트 클래스의 Inner클래스로 만들고
 * 프로덕션 코드로 옮기는게 정석
 */

/**
 * @DataJpaTest
 * JAP Repository들에 대한 빈들을 등록하여 단위 테스트의 작성을 용이하게 함
 * @ExtendWith({SpringExtension.class}) : 테스트 컨텍스트를 잡아준다.
 * @Transactional : 테스트의 롤백 등을 위해 별도의 트랜잭션 어노테이션을 추가 안해도된다.
 *
 */

@DataJpaTest
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;

    /**
     * @Entity 없을 때
     * Error creating bean with name 'membershipRepository' defined in com.example.tddspring.MembershipRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Not a managed type: class com.example.tddspring.Membership
     *
     * @Id가 없을 때
     * Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: No identifier specified for entity: com.example.tddspring.Membership
     */
    @Test
    public void membershipRepository가Null아님(){

        Assertions.assertThat(membershipRepository).isNotNull();
    }


    @Test
    public void 멤버십등록(){
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        Membership result = membershipRepository.save(membership);
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getUserId()).isEqualTo("userId");
        Assertions.assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        Assertions.assertThat(result.getPoint()).isEqualTo(10000);


    }

    /**
     * 이미 멤버십타입을 등록했으면 중복 검사를 히용하여 등록되지 않도록 해야한다.
     */
    @Test
    public void 멤버십이존재하는지테스트(){
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10000)
                .build();

        membershipRepository.save(membership);
        Membership result = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getUserId()).isEqualTo("userId");
        Assertions.assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        Assertions.assertThat(result.getPoint()).isEqualTo(10000);

    }
}
