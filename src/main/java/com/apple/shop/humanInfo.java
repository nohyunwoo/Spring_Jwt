package com.apple.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class humanInfo {

    private String name;
    private Integer age;

    public void addAge() {
        this.age = age + 1;
    }

    public void 나이설정(Integer age) {
        if (age > 0 && age < 100) {
            this.age = age;
        }

    }
}
