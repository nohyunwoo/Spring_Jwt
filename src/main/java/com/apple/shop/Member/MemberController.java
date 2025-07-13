package com.apple.shop.Member;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder; // Spring Security에서 현재 로그인한 사용자 정보를 가져올 수 있게 해주는 클래스
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
// 생성자 자동 주입
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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

    @PostMapping("/login/jwt")
    @ResponseBody
    public String loginJWT(@RequestBody Map<String, String> data,
                           HttpServletResponse response){

        // 아이디, 비번을 Authentication 객체로 생성
        var authToken = new UsernamePasswordAuthenticationToken(
                data.get("username"),
                data.get("password")
        );
        // DB에 저장된 아이디, 비번과 비교하며 인증
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);

        //보안 컨텍스트에 저장(인증된 사용자라고 인식)
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 현재 로그인된 인증 객체를 가져와 토큰 생성
        var jwt = JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(jwt);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(60*30);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return jwt;
    }

    @ResponseBody
    @GetMapping("/my-page/jwt")
    String myPageJWT(Authentication auth){

        CustomUser user = (CustomUser) auth.getPrincipal();
        System.out.println(user);
        System.out.println(user.displayName);
        System.out.println(user.getAuthorities());
        return "마이페이지";
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
