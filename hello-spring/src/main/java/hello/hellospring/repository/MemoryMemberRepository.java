package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// MemberRepository 인터페이스에 대한 메모리 구현체
//@Repository
public class MemoryMemberRepository implements MemberRepository { // alt+enter로 implement method

    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키값 생성 용. 0,1,2,,

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null인 경우 Optional로 감싸서 리턴
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 자바8 람다
                .filter(member -> member.getName().equals(name)) // 멤버 getName이 파라미터로 넘어온 name과 같은지 확인. 같은 경우만 필터링 됨
                .findAny(); // 하나라도 찾기. 없으면 null
    }

    @Override
    public List<Member> findAll() { // 실무에선 list 많이 씀
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
