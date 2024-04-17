package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordController {

    @GetMapping("/record")
    public String record(){
        return "record/record";
    }

    @GetMapping("/record/{gameNameAndTagLine}")
    public String recordSearch(){
        return "record/record";
    }

}
