package fourjo.idle.goodgame.gg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/emp")
    public String employeeInsert() {
        return "emp";
    }
    @GetMapping("/emp/employeeDelete")
    public String employeeDelete() {
        return "emp/delete";
    }
    @GetMapping("/emp/employeeUpdate")
    public String employeeUpdate() {
        return "emp/update";
    }
    @GetMapping("/emp/employeeSearch")
    public String employeeSearch() {
        return "emp/search";
    }
    @GetMapping("/emp/insertRoleId")
    public String insertRoleId() {
        return "emp/insertRoleId";
    }

}
