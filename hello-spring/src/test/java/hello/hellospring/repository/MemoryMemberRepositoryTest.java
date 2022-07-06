package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest { // 다른곳에서 가져다 쓸 것 아니기 때문에 굳이 public 안해도 됨
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 순서는 보장 안됨. 따라서 테스트 하나 끝날 때마다 리파지토리 깔끔하게 지워야함

    @AfterEach
    public void afterEach() { // 테스트 메서드들 끝날때마다 동작. 콜백 메소드
        repository.clearStore();
    }

    @Test
    public void save() { // 만들어둔 save가 동작하는지 보면 됨
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // 반환 타입이 Optional일 때 값 꺼낼때는 get() 사용
        // System.out.println("result = " + (result == member)); // result = true
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result); // Assertions에서 ant+enter 누르고 static import 하면 Assertions 안써도 됨
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift+f6 하면 같은 변수명 전체 바꾸기
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();// ctrl+alt+v 하면 리턴값 맞춰서 반환 변수 자동 추가
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findALl() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
