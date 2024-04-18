package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordController {

    @GetMapping("/record/{gameNameAndTagLine}")
    public String record(){
        return "record/record.html";
    }
}
