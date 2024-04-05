package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/userUpdate")
    public String userUpdate(){
        return "admin/userUpdate";
    }
    @GetMapping("/userDelete")
    public String userDelete(){
        return "admin/userDelete";
    }

}
