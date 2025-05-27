package com.apple.shop.sales;

import com.apple.shop.Member.CustomUser;
import com.apple.shop.Member.Member;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    @PostMapping("/order")
    String postOrder(@RequestParam String title,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth){
        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        Member member = new Member();
        member.setId(user.id);
        sales.setMember(member);
        salesRepository.save(sales);


        return "redirect:/list/page/1";
    }

    @GetMapping("/order/all")
    String getOrderAll(){

        List<Sales> all = salesRepository.customFindAll();
        System.out.println("all = " + all);
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.itemName = all.get(0).getItemName();
        salesDTO.price = all.get(0).getPrice();
        salesDTO.username = all.get(0).getMember().getUsername();
        return "salesDTO";
    }


}

class SalesDTO{
    public String itemName;
    public Integer price;
    public String username;
}
