package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping("")
    public String mypage(){return "/mypage/main.html";}

    @GetMapping("/update")
    public String update(){return "/mypage/update.html";}

    @GetMapping("/delete")
    public String delete(){return "/mypage/delete.html";}

    @GetMapping("/post")
    public String post(){return "/mypage/board.html";}

    @GetMapping("/reply")
    public String reply(){return "/mypage/reply.html";}
//
//    @GetMapping("/mbti")
//    public String mbti(){return "/mypage/mbti.html";}




}
