package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.security.PrincipalDetails;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.account.EmpDto;
import fourjo.idle.goodgame.gg.web.dto.account.UserDto;
import fourjo.idle.goodgame.gg.web.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Tag(name ="User Api", description = "User Api 입니다. User에 관한 api가 있습니다.")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    @Operation(summary ="회원가입", description = "조건에 맞으면 회원가입이 진행됩니다." )
    public ResponseEntity<CMRespDto<?>> registerUser (@RequestBody UserDto userDto, BindingResult bindingResult){
        userService.registerUser(userDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/register/emp")
    @Operation(summary ="회원가입", description = "조건에 맞으면 회원가입이 진행됩니다." )
    public ResponseEntity<CMRespDto<?>> registerEmp (@RequestBody EmpDto empDto, BindingResult bindingResult){
        userService.registerEmp(empDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping("/principal")
    @Operation(summary ="권한 확인", description = "로그인할때 권환을 확인합니다." )
    public ResponseEntity<CMRespDto<? extends PrincipalDetails>> getPrincipalDetails (@AuthenticationPrincipal PrincipalDetails principalDetails){

        if(principalDetails != null){
            principalDetails.getAuthorities().forEach(role -> {
                log.info("로그인된 사용자의 권한 {}", role.getAuthority());
            });
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "failed login", null));
        }

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", principalDetails));
    }


}
