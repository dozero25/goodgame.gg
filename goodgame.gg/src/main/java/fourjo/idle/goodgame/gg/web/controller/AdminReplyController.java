package fourjo.idle.goodgame.gg.web.controller;

import fourjo.idle.goodgame.gg.web.service.AdminReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminReplyController {
@Autowired
    AdminReplyService adminReplyService;
    @GetMapping("/ReplyDeleteByIndex")
    public String replyDeleteByUserIndex(){
        return "admin/AdminReplyDelete";
    }
    @GetMapping("/AdminReplySearch")
    public String replySearchByUserNickAndReplyContent(){
        return "admin/AdminReplySearch";
    }
}
