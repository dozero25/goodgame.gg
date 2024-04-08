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
    public String userUpdateUserNickByUserIndex(){
        return "admin/AdminUserUpdate";
    }
    @GetMapping("/AdminUserDelete")
    public String userDeleteByUserIndex(){
        return "admin/AdminUserDelete";
    }

    @GetMapping("/AdminUserSearch")
    public String userSearchByUserNickAndEmailAndId(){
        return "admin/AdminUserSearch";
    }
    @GetMapping("/AdminUserSelectOne")
    public String userSelectOneByUserIndex(){
        return "admin/AdminUserSelectOne";
    }
}
