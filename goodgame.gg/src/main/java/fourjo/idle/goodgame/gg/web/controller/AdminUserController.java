package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.web.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {
@Autowired
AdminUserService adminUserService;
    @GetMapping("/AdminUserUpdate")
    public String AdminUserUpdate(){
        return "admin/AdminUserUpdate";
    }
    @GetMapping("/AdminUserDelete")
    public String AdminUserDelete(){
        return "admin/AdminUserDelete";
    }
    @GetMapping("/AdminUserSelectAll")
    public String AdminUserSelectAll(){
        return "admin/AdminUserSelectAll";
    }

    @GetMapping("/AdminUserSearch")
    public String AdminUserSearch(){
        return "admin/AdminUserSearch";
    }
    @GetMapping("/AdminUserSelectOne")
    public String AdminUserSelectOne(){
        return "admin/AdminUserSelectOne";
    }
}
