package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecordController {

    @GetMapping("/record}")
    public String record(){
        return "record/record.html";
    }
    
}
