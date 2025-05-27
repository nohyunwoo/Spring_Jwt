package com.apple.shop.Item;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;

@Controller
// 생성자 자동 주입
@RequiredArgsConstructor
public class ItemController {
    // 원하는 클래스에 repository 등록
    private final ItemRepository itemRepository;
    // 사용할 곳에 변수로 등록
    private final ItemService itemService;

    private final CommentRepository commentRepository;


    @GetMapping("/list")
    public String showList(Model model){
        model.addAttribute("item", itemService.listItem());
        return "list.html";
    }

    @GetMapping("/write")
    public String write(){
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        List<Comment> byParentId = commentRepository.findByParentId(id);
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("item",itemService.findObjectId(id).get());
            model.addAttribute("comment",byParentId);
            return "detail.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @GetMapping("/update/{id}")
    public String getUpdateId(@PathVariable Long id, Model model){
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("upItem",itemService.findObjectId(id).get());
            return "update.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @GetMapping("/test1")
    public String test(@RequestParam String name, @RequestParam Integer age){
        System.out.println(name + age);
        return "redirect:/list";
    }

    @PostMapping("/add")
    public String addPost(String title, Integer price) { // String title, Integer price
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        itemService.saveItem(title, price, username);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delList(@RequestParam Long id) { // String title, Integer price
        itemService.deleteItem(id);
        return "Deleted item with id: " + id;
    }

    @PostMapping("/update/{id}")
    public String updateId(@PathVariable Long id, Model model){
        if (itemService.findObjectId(id).isPresent()) {
            model.addAttribute("upItem",itemService.findObjectId(id).get());
            return "update.html";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam Integer price){
        if (itemService.findObjectId(id).isPresent()) {
            itemService.updateItem(id, title, price);
            return "redirect:/list";
        } else {
            return "redirect:/detail/{id}";
        }
    }

    @GetMapping("/list/page/{n}")
    public String getList(Model model, @PathVariable Integer n){
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(n-1, 5));
        model.addAttribute("item", result);
        model.addAttribute("currentPage", n);
        model.addAttribute("totalPages", result.getTotalPages());
        return "list.html";
    }

    @GetMapping("/search")
    public String postSearch(@RequestParam String searchText, Model model){
        List<Item> allByTitleContains;
        if (searchText != null && !searchText.isEmpty()) {
            allByTitleContains = itemRepository.rawQuery1(searchText);
        }
        else {
            allByTitleContains = itemRepository.findAll();
        }

        model.addAttribute("item", allByTitleContains);
        return "list.html";
    }

}




