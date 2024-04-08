package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.admin.AdminUserSearchDTO;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.admin.UserDto;
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
@RequestMapping("/api/admin")
@Tag(name ="adminUserApi", description = "Admin이 User정보를 관리하는 Api 입니다.")
public class AdminUserApi {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/user/update")
//    @Operation(summary="회원정보 수정",description="Admin이 User의 정보를 수정합니다")
    public ResponseEntity<CMRespDto<?>> userUpdateUserNickByUserIndex (@RequestBody UserDto userDto, BindingResult bindingResult){
        adminUserService.userUpdateUserNickByUserIndex(userDto);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }
    @DeleteMapping("/user/delete")
//    @Operation(summary="회원정보 삭제",description="Admin이 userIndex를 활용해 해당 User의 정보를 삭제합니다")
    public ResponseEntity<CMRespDto<?>> userDeleteByUserIndex (int userIndex){
        adminUserService.userDeleteByUserIndex(userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @GetMapping("/user/search")
//    @Operation(summary="회원정보 검색",description="Admin이 User의 정보를 검색합니다(모두,in=아이디/닉네임,i=아이디,n=닉네임,e=이메일")
    public ResponseEntity<CMRespDto<?>> userSearchByUserNickAndEmailAndIdv(AdminUserSearchDTO AdminUserSearchDTO) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",    adminUserService.userSearchByUserNickAndEmailAndId(AdminUserSearchDTO)));
    }
    @GetMapping("/user/selectOne")
//    @Operation(summary="회원정보 자세히",description="Admin이 userIndex를 활용해 해당 User의 정보를 자세히 확인합니다")
    public ResponseEntity<CMRespDto<?>> userSelectOneByUserIndex(int userIndex) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully",    adminUserService.userSelectOneByUserIndex(userIndex)));
    }
}
