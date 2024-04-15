package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

    @GetMapping("/index")
    public String index(){return "board/index";
    }

    @GetMapping("/save")
    public String save(){return "board/save";
    }



}
