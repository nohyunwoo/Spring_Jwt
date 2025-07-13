package com.apple.shop.sales;

import com.apple.shop.Member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer price;
    private Integer count;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY) // EAGER: 필요 없어도 미리 가져와달라, LAZY: 필요할 때 가져와달라
    @JoinColumn(
            name = "member_Id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @CreationTimestamp
    private LocalDateTime created;
}
