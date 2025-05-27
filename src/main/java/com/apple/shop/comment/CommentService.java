package com.apple.shop.comment;

import com.apple.shop.Member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveData(String comment,Long parent, String username){
        Comment data = new Comment();
        data.setContent(comment);
        data.setUsername(username);
        data.setParentId(parent);

        commentRepository.save(data); // 내용 저장
    }
}
