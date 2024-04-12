package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/login/fail")
    public String loginFail(){
        return "login/loginfail";
    }

    @GetMapping("/register")
    public String register(){return "register/register";}

}
