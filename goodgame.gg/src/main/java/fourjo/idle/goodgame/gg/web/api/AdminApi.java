package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.AdminBoardDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.service.AdminService;
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
@RequestMapping("/api/admin_board")
@Tag(name ="admin_board_Api", description = "admin board Api 입니다.")
public class AdminApi {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin_board_insert")
    public ResponseEntity<CMRespDto<?>> admin_board_insert (@RequestBody AdminBoardDTO dto, BindingResult bindingResult){
        log.info("DTO:{}",dto);
        adminService.admin_board_insert(dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/admin_board_update")
    public ResponseEntity<CMRespDto<?>> admin_board_update (@RequestBody AdminBoardDTO dto){
        adminService.admin_board_update(dto);
        log.info("DTO:{}",dto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/admin_board_delete")
    public ResponseEntity<CMRespDto<?>> admin_board_delete (int board_index){
        log.info("Board_index:{}",board_index);
        adminService.admin_board_delete(board_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping ("/admin_board_select")
    public ResponseEntity<CMRespDto<?>> admin_board_selectAll (){
        log.info("admin_board_selectAll");
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", adminService.admin_board_selectAll()));
    }

}
