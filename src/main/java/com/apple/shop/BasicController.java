package com.apple.shop;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


// Controller 붙이면 그 해당 클래스에 서버기능 코드 작성 가능
@Controller
public class BasicController {
    @GetMapping("/")
    String mainPage(){
        return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String aboutPage(){
        return "About page test";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String myPage(){
        return "This my page";
    }



}
