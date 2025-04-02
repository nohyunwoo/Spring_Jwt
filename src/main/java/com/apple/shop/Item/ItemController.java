package com.apple.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    String detail(@PathVariable Long id, Model model) {
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("item",itemService.findObjectId(id).get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/update/{id}")
    String getUpdateId(@PathVariable Long id, Model model){
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("upItem",itemService.findObjectId(id).get());
            return "update.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @GetMapping("/test1")
    String test(@RequestParam String name, @RequestParam Integer age){
        System.out.println(name + age);
        return "redirect:/list";
    }

//    @PostMapping("/test1")
//    String test1(@RequestBody Map<String, Object> body){
//        System.out.println(body.get("name"));
//        return "redirect:/list";
//    }

    @PostMapping("/add")
    String addPost(String title, Integer price) { // String title, Integer price
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @PostMapping("/update/{id}")
    String updateId(@PathVariable Long id, Model model){
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("upItem",itemService.findObjectId(id).get());
            return "update.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @PostMapping("/update")
    String update(@RequestParam Long id, @RequestParam String title, @RequestParam Integer price){
        if (itemService.findObjectId(id).isPresent()) {
            itemService.updateItem(id, title, price);
            return "redirect:/list";
        } else {
            return "redirect:/detail/{id}";
        }
    }
}




