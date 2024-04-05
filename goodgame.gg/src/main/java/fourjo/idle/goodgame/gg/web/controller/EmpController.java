package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpController {

    @GetMapping("/emp")
    public String empInsert (){
        return "emp";
    }

    @GetMapping("/emp/delete")
    public String empDelete (){
        return "emp/delete";
    }

    @GetMapping("/emp/update")
    public String empUpdate (){
        return "emp/update";
    }

}
