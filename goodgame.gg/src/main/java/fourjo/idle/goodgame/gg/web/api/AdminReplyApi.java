package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.AdminReplySearchDTO;
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
@RequestMapping("/api/admin_reply")
@Tag(name ="Admin Reply Api", description = "Admin Reply Api 입니다.")
public class AdminReplyApi {

    @Autowired
    private AdminReplyService adminReplyService;

    @DeleteMapping("/AdminReplyDelete")
    public ResponseEntity<CMRespDto<?>> AdminReplyDelete (int reply_index){
        adminReplyService.AdminReplyDelete(reply_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/AdminReplySearch")
    public ResponseEntity<CMRespDto<?>> AdminReplySearch (AdminReplySearchDTO adminReplySearchDTO){

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", adminReplyService.AdminReplySearch(adminReplySearchDTO)));
    }
}
