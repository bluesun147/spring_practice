package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 어노테이션
public class HelloController {
    // Get은 http 메서드
    @GetMapping("hello") // 웹 어플리케이션에서 /hello 들어가면 이 메서드 실행
    public String hello(Model model) { // mvc에서의 그 모델. 스프링이 모델 만들어서 넣어줌
        model.addAttribute("data", "spring!!"); // 키, 값
        return "hello"; // 뷰 리졸버(viewResolver) templates 밑 hello.html 찾음
    }
}
