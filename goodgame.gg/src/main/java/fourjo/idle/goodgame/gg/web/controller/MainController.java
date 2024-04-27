package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main (){return "main/main.html";}

    @GetMapping("/record")
    public String record(){
        return "record/record";
    }

    @GetMapping("/record/{gameNameAndTagLine}")
    public String recordSearch(){
        return "record/record";
    }

    @GetMapping("/ranking")
    public String ranking () {return "ranking/main";}

    @GetMapping("/duo")
    public String duo(){return "duo/main";}

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/lolbti")
    public String lolbti(){
        return "lolbti/lolbti";
    }

    @GetMapping("/slide")
    public String slide () {return "rotations/slide";}



}
