package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.ReplyDto;
import fourjo.idle.goodgame.gg.web.service.AdminReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin_reply")
@Tag(name ="Admin Reply Api", description = "Admin Reply Api 입니다.")
public class AdminReplyApi {

    @Autowired
    private AdminReplyService adminReplyService;

    @PostMapping("/AdminReplyDelete")
    public ResponseEntity<CMRespDto<?>> AdminReplyDelete (int reply_index){
        adminReplyService.AdminReplyDelete(reply_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

}
