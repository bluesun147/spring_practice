package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 어노테이션 있으면 스프링 컨테이너에서 자동으로 생성
public class MemberController {
    private final MemberService memberService; // 스프링 컨테이너에 등록하고 하나만 씀. 계속 new로 새로운 객체 만드는 것 아니라

    @Autowired // 컨트롤러 생성될 때 스프링 빈에 등록되어 있는 서비스 객체 가져다 넣어줌: DI(의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
