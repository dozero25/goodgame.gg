package fourjo.idle.goodgame.gg.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/duo")
public class DuoController {
    @GetMapping("")
    public String duoMain(){

        return "duo/main";}


}
