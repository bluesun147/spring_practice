package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    // 테스트 멤버 레파지토리는 서비스에서 쓰는 레파지토리와 다른 객체. (new로 새로 만듬)
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // service만으론 클리어 못하므로 리파지토리 가져옴
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { // 테스트 실행 전에 레파지토리 만들고 서비스에 넣음. -> 같은 레파지토리 사용: 외부에서 넣어줌. DI
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 없으면 회원가입, 중복회원조회에 둘 다 "spring" 주면 오류
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}