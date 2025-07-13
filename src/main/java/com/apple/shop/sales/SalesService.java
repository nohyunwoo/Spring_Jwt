package com.apple.shop.sales;

import com.apple.shop.Member.CustomUser;
import com.apple.shop.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public void saveOrder(String title, Integer price, Integer count, Member member){
        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        sales.setMember(member);
        salesRepository.save(sales);
    }

}
