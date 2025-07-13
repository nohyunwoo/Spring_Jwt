package com.apple.shop.Member;

import com.apple.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String displayName;

    @OneToMany(mappedBy = "member") // 내 정보를 뽀려가는 행들 자동 출력
    List<Sales> sales = new ArrayList<>();
}


