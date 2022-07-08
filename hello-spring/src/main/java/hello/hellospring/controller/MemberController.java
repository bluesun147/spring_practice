package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션 있으면 스프링 컨테이너에서 자동으로 생성
public class MemberController {
    private final MemberService memberService; // 스프링 컨테이너에 등록하고 하나만 씀. 계속 new로 새로운 객체 만드는 것 아니라

    // 생성자 주입 추천
    @Autowired // 컨트롤러 생성될 때 스프링 빈에 등록되어 있는 서비스 객체 가져다 넣어줌: DI(의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; // resources/members/createMemberForm.html
    }

    @PostMapping("/members/new") // http 메서드가 post
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입 끝나면 홈으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 멤버를 모델에 담아서 화면에 넘김
        return "members/memberList";
    }
}
