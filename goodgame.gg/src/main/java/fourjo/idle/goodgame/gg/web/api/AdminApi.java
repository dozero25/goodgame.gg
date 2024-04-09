package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.Admin.AdminBoardDTO;
import fourjo.idle.goodgame.gg.web.dto.Admin.AdminBoardSearchDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name ="Admin Board Api", description = "Admin Board Api 입니다.")
public class AdminApi {

    @Autowired
    private AdminService adminService;

    @Operation( summary = "게시글 삽입 Api", description = "게시글을 userIndex를 기준으로 작성합니다.")
    @PostMapping("/board/insert")
    public ResponseEntity<CMRespDto<?>> boardInsertByUserIndex (@RequestBody AdminBoardDTO adminBoardDTO, BindingResult bindingResult){
        adminService.boardInsertByUserIndex(adminBoardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @Operation( summary = "게시글 수정 Api", description = "게시글을 boardIndex를 기준으로 수정합니다.")
    @PatchMapping("/board/update")
    public ResponseEntity<CMRespDto<?>> boardUpdateByBoardIndex (@RequestBody AdminBoardDTO adminBoardDTO){
        adminService.boardUpdateByBoardIndex(adminBoardDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

//    @Operation( summary = "게시글 삭제 Api", description = "게시글을 boardIndex를 기준으로 삭제합니다.")
    @DeleteMapping("/board/delete")
    public ResponseEntity<CMRespDto<?>> boardDeleteByBoardIndex (int boardIndex){
        adminService.boardDeleteByBoardIndex(boardIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }


    @Operation( summary = "게시글 검색 Api", description = "게시글을 제목+내용,제목,내용,닉네임,아이디 로 검색 합니다.")
    @GetMapping ("/board/search")
    public ResponseEntity<CMRespDto<?>> boardSearchAllBySubjectAndContentAndNickAndId (AdminBoardSearchDTO adminBoardSearchDTO){
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", adminService.boardSearchAllBySubjectAndContentAndNickAndId(adminBoardSearchDTO)));
    }

}
