package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ranking")
public class RankingController {
    @GetMapping("")
    public String ranking () {return "ranking/main";}

    @GetMapping("/slide")//테스트용
    public String rotations () {return "ranking/slide";}

}
