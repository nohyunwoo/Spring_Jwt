package com.apple.shop.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController  {

    // 원하는 클래스에 repository 등록
    private final PostRepository postRepository;
    @GetMapping("/post")
    String showList(Model model){
        List<Post> result = postRepository.findAll();
        model.addAttribute("post", result);

        Post a = new Post();
        System.out.println("a = " + a);

        return "Post.html";
    }

}

