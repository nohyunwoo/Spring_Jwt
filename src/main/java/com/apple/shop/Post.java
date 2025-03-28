package com.apple.shop;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "글제목")
    public String title;

    @Column(name = "날짜")
    public String date;
}
//insert into post values (1,"이번 주 할인이벤트합니다.", "2025년 3월 24일");
//insert into post values (2,"입금자명 제발 쓰세요", "2025년 3월 24일");
//insert into post values (3,"소비자 브랜드상 수상", "2025년 3월 24일");
