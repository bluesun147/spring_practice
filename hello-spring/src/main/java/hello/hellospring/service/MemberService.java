package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService { // ctrl+shift+t 테스트 자동 생성
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // final은 수정 불가
    private final MemberRepository memberRepository;

    @Autowired // 서비스 생성 시 생성자 호출 -> 리포지토리 서비스에 주입해줌
    public MemberService(MemberRepository memberRepository) { // 직접 new로 생성하는 것이 아니라 외부에서 넣어주도록
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 x
        // ctrl+alt+v 로 리턴 변수 자동 생성
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // result에 값이 있으면 (null 아니고). ifPresent는 Optional 메서드. null일 가능성 있다면 Optional 쓰자
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

//        memberRepository.findByName(member.getName()) // ctrl+alt+l 코드 정렬
//                .ifPresent(m -> { // result에 값이 있으면 (null 아니고). ifPresent는 Optional 메서드. null일 가능성 있다면 Optional 쓰자
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // ctrl+alt+m 으로 extract method. 메서드 생성해줌
        memberRepository.findByName(member.getName()) // ctrl+alt+l 코드 정렬
                .ifPresent(m -> { // result에 값이 있으면 (null 아니고). ifPresent는 Optional 메서드. null일 가능성 있다면 Optional 쓰자
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
