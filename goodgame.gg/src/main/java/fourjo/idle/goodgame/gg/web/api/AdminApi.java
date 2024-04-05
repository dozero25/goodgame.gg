package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import fourjo.idle.goodgame.gg.web.service.AdminService;
import fourjo.idle.goodgame.gg.web.service.UserService;
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
@RequestMapping("/api/admin")
@Tag(name ="Admin Api", description = "Admin Api 입니다.")
public class AdminApi {

    @Autowired
    private AdminService adminService;

    @PostMapping("/userUpdate")
    public ResponseEntity<CMRespDto<?>> userUpdate (@RequestBody UserDto userDto, BindingResult bindingResult){
        adminService.userUpdate(userDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @PostMapping("/userDelete")
    public ResponseEntity<CMRespDto<?>> userDelete (@RequestBody UserDto userDto, BindingResult bindingResult){
        adminService.userDelete(userDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

}
