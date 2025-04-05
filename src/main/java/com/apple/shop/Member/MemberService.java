package com.apple.shop.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(String username, String password, String displayName) {
        Member member = new Member();
        member.setUsername(username);
        String encode = passwordEncoder.encode(password);
        member.setPassword(encode);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
