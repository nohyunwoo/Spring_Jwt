package com.apple.shop.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// 생성자 자동 주입
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/login")
    String loginPage(){
        return "login.html";
    }

    @PostMapping("/login")
    String loginSave(@RequestParam String username,@RequestParam String password,
                     @RequestParam String displayName ){
        memberService.saveUser(username, password, displayName);
        return "redirect:/list";
    }

}
