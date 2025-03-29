package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
// 생성자 자동 주입
@RequiredArgsConstructor
public class ItemController {
    // 원하는 클래스에 repository 등록
    private final ItemRepository itemRepository;
    // 사용할 곳에 변수로 등록
    private final ItemService itemService;


    @GetMapping("/list")
    String showList(Model model){
        model.addAttribute("item", itemService.listItem());
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable long id, Model model) {
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("item",itemService.findObjectId(id).get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) { // String title, Integer price
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @PostMapping("/update/{id}")
    String update(@PathVariable Long id, Model model){
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("upItem",itemService.findObjectId(id).get());
            return "update.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }



}
