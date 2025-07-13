package com.apple.shop.sales;

import com.apple.shop.Member.CustomUser;
import com.apple.shop.Member.Member;
import com.apple.shop.Member.MemberRepository;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;
    private final SalesService salesService;
    private final MemberRepository memberRepository;

    @PostMapping("/order")
    String postOrder(@RequestParam String title,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth){
        CustomUser user = (CustomUser) auth.getPrincipal();
        Member member = new Member();
        member.setId(user.id);
        salesService.saveOrder(title, price, count, member);

        return "redirect:/list/page/1";
    }

    @GetMapping("/order/all")
    String getOrderAll(){
//        List<Sales> all = salesRepository.customFindAll();
//        System.out.println("all = " + all);
//        SalesDTO salesDTO = new SalesDTO();
//        salesDTO.itemName = all.get(0).getItemName();
//        salesDTO.price = all.get(0).getPrice();
//        salesDTO.username = all.get(0).getMember().getUsername();
        Optional<Member> byId = memberRepository.findById(1L);
        System.out.println("byId = " + byId.get());
        return "sales.html";
    }


}

class SalesDTO{
    public String itemName;
    public Integer price;
    public String username;
}
