package com.apple.shop.comment;

import com.apple.shop.Member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/comment")
    String postComment(@RequestParam String comment, @RequestParam Long parent, Authentication auth){
        CustomUser user = (CustomUser)auth.getPrincipal();
        String username = user.getUsername();

        commentService.saveData(comment, parent, username);

        return "redirect:/list";
    }

}
