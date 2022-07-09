package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa는 EntityManager로 모든 것 동작
    // 스프링 부트가 자동으로 EntityManager 생성하고
    // db와 연결 알아서 다 해줌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 영속하다, 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny(); // result를 stream으로 변환해서
    }

    @Override
    public List<Member> findAll() {
        // jpql: sql은 테이블 대상으로 쿼리 날림. jpql은 객체(엔티티) 대상.
        // 객체 자체를 select. (m)
        List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
        return result;
    }
}
