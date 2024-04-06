package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import fourjo.idle.goodgame.gg.web.service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin_user")
@Tag(name ="Admin User Api", description = "Admin User Api 입니다.")
public class AdminUserApi {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/AdminUserUpdate")
    public ResponseEntity<CMRespDto<?>> AdminUserUpdate (@RequestBody UserDto userDto, BindingResult bindingResult){
        adminUserService.AdminUserUpdate(userDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @PostMapping("/AdminUserDelete")
    public ResponseEntity<CMRespDto<?>> AdminUserDelete (int user_index){
        adminUserService.AdminUserDelete(user_index);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @GetMapping("/all/AdminUserSelectAll")
    public ResponseEntity<CMRespDto<?>> AdminUserSelectAll( ) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", adminUserService.AdminUserSelectAll()));
    }
    @GetMapping("/all/AdminUserSearchList")
    public ResponseEntity<CMRespDto<?>> AdminUserSearchList(String user_nick) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",    adminUserService.AdminUserSearchList(user_nick)));
    }
    @GetMapping("/all/AdminUserSelectOne")
    public ResponseEntity<CMRespDto<?>> AdminUserSelectOne(int user_index) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",    adminUserService.AdminUserSelectOne(user_index)));
    }
}
