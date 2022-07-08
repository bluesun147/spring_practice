package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
// 레파지토리 - 인터페이스
// 구현체 - MemoryMemberRepository

public interface MemberRepository {
    Member save(Member member); // 회원 저장 후 저장 회원 반환
    Optional<Member> findById(Long id); // id로 회원 조회
    Optional<Member> findByName(String name); // null 반환 대신 Optional로 감싸서 나감.
    List<Member> findAll(); // 저장된 모든 회원 리스트 반환
}
