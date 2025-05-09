package com.apple.shop.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 알아서 원하는 함수를 만들어줌
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(long id);
}
