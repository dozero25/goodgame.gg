package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.web.dto.UserDto;
import fourjo.idle.goodgame.gg.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
@Autowired
AdminService adminService;
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

    @GetMapping("/AdminUserSearchList")
    public String AdminUserSearchList(){
        return "admin/AdminUserSearchList";
    }
    @GetMapping("/AdminUserSelectOne")
    public String AdminUserSelectOne(){
        return "admin/AdminUserSelectOne";
    }
}
