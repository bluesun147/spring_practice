package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // db에 테스트로 데이터 넣고 테스트 끝나면 다 롤백. 회원가입 테스트 다시해도 오류 x
class MemberServiceIntegrationTest {

    // 테스트는 제일 끝단이므로 생성자 인젝션 안하고 편하게 필드 인젝션 해도 됨.
    @Autowired MemberService memberService;
    // 테스트 멤버 레파지토리는 서비스에서 쓰는 레파지토리와 다른 객체. (new로 새로 만듬)
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // service만으론 클리어 못하므로 리파지토리 가져옴
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() { // 테스트는 한글로 써도 됨
        // given 뭔가 주어졌고
        Member member = new Member();
        member.setName("spring");

        // when 이것을 실행 했을 때
        Long saveId = memberService.join(member);

        // then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // alt+enter static import
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }
}