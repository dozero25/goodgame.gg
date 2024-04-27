package fourjo.idle.goodgame.gg.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller

public class MainController {
    @GetMapping("/duo")
    public String duo(){

        return "duo/main";}
    @GetMapping("/ranking")
    public String ranking () {return "ranking/main";}
    @GetMapping("/slide")
    public String slide () {return "rotations/slide";}

}
