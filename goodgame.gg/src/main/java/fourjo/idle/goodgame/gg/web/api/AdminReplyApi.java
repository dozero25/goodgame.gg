package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.admin.AdminReplySearchDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.service.AdminReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name ="adminReplyApi", description = "Admin이 Reply를 관리하는 Api 입니다.")
public class AdminReplyApi {

    @Autowired
    private AdminReplyService adminReplyService;

    @DeleteMapping("/reply/delete")
    public ResponseEntity<CMRespDto<?>> replyDeleteByUserIndex (int replyIndex){
        adminReplyService.replyDeleteByUserIndex(replyIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/reply/search")
    public ResponseEntity<CMRespDto<?>> replySearchByUserNickAndReplyContent (AdminReplySearchDTO adminReplySearchDTO){

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", adminReplyService.replySearchByUserNickAndReplyContent(adminReplySearchDTO)));
    }
}
