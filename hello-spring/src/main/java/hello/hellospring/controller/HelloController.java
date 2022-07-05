package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러 어노테이션
public class HelloController {
    // Get은 http 메서드
    @GetMapping("hello") // 웹 어플리케이션에서 /hello 들어가면 이 메서드 실행
    public String hello(Model model) { // mvc에서의 그 모델. 스프링이 모델 만들어서 넣어줌
        model.addAttribute("data", "spring!!"); // 키, 값
        return "hello"; // 뷰 리졸버(viewResolver) templates 밑 hello.html 찾음
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // param으로 name 받기, model 넘겨줌, 모델에 담으면 뷰에서 렌더링
        model.addAttribute("name", name); // 키, 값
        return "hello-template"; // templates 폴더에 hello-template.html 찾음
    }
}
