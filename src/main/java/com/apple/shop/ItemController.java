package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    // 원하는 클래스에 repository 등록
    private final ItemRepository itemRepository;
    @GetMapping("/list")
    String showList(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("item", result);
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable long id, Model model) throws Exception {
        Optional<Item> byId = itemRepository.findById(id);

        throw new Exception();
    }

    @PostMapping("/add")
    String addPost(@ModelAttribute Item item) { // String title, Integer price
        itemRepository.save(item);
        return "redirect:/list";
    }



}
