package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 컴포넌트 스캔, 자동 의존관계 설정(어노테이션) 대신 직접 코드로 스프링 빈 등록
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔 사용.
// 정형화 되지 않거나 상황에 따라 구현 클래스 변경해야 한다면 설정 통해 스프링 빈 등록

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired // 스프링 컨테이너에서 MemberRepository 찾음. 자동 등록된 SpringDataJpaMemberRepository 구현체
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // 내가 스프링 빈 등록할것이라는 뜻
    public MemberService memberService() {
        return new MemberService(memberRepository); // ctrl+p 무엇을 넣어야 할 지
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository(); // MemberRepository는 인터페이스. 인터페이스는 new가 안됨
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
