package com.apple.shop.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder; // Spring Security에서 현재 로그인한 사용자 정보를 가져올 수 있게 해주는 클래스
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
// 생성자 자동 주입
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/register")
    public String register(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/list";
        }
        return "register.html";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){
        CustomUser principal = (CustomUser)auth.getPrincipal();
        System.out.println(principal.displayName);
        return "mypage.html";
    }

    @PostMapping("/register")
    public String registerSave(@RequestParam String username,@RequestParam String password,
                     @RequestParam String displayName ){
        memberService.saveUser(username, password, displayName);
        return "redirect:/login";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDTO getUser(){
        Optional<Member> byId = memberRepository.findById(1L);
        var result = byId.get();
        MemberDTO data = new MemberDTO(result.getUsername(), result.getDisplayName());
        MemberDTO data2 = new MemberDTO(result.getUsername(), result.getDisplayName(), result.getId());
        return data2;
    }
}

// DTO: 데이터 변환형 클래스
class MemberDTO {
    // public이 붙어있어야 json으로 변환 가능, @Getter 쓰거나
    public String username;
    public String displayName;
    public Long id;

    MemberDTO(String username, String displayName){
        this.username = username;
        this.displayName = displayName;
    }
    MemberDTO(String username, String displayName, Long id){
        this.username = username;
        this.displayName = displayName;
        this.id = id;
    }
}
