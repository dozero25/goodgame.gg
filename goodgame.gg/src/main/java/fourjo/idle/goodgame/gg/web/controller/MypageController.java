package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MypageController {

    @GetMapping("/mypage")
    public String mypage(){return "/mypage/main.html";}

    @GetMapping("/update")
    public String update(){return "/main/update.html";}

    @GetMapping("/delete")
    public String delete(){return "/main/delete.html";}

    @GetMapping("/post")
    public String post(){return "/main/post.html";}

    @GetMapping("/reply")
    public String reply(){return "/main/reply.html";}

    @GetMapping("/mbti")
    public String mbti(){return "/main/mbti.html";}

    @GetMapping("/css")
    public String css(){return "/mypage/css.html";}


}
