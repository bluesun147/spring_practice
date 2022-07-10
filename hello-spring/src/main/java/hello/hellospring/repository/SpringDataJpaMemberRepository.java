package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
// 인터페이스가 인터페이스 받을때는 extends
// 구현체 없어도 됨
// 인터페이스만 있음 -> 스프링 데이터 jpa가 JpaRepository 받고 있으면 구현체 자동으로 만들고
// 자동으로 스프링 빈에 등록.
// config에서 그냥 가져다 쓰면 됨.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // <T, id(식별자 pk)>, 다중 상속
    // JPQL: select m from Member m where m.name = ?
    Optional<Member> findByName(String name);
}