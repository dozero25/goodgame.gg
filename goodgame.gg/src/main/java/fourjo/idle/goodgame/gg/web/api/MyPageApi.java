package fourjo.idle.goodgame.gg.web.api;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.Matcher;
import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.ReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import fourjo.idle.goodgame.gg.web.service.MyPageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
@Tag(name = "Mypage Api", description = "Mypage Api 입니다.")
public class MyPageApi {

    @Autowired
    private MyPageService myPageService;

    /*0. 더미데이터 생성*/
    @PostMapping("/insert")
    public ResponseEntity<CMRespDto<?>> insertUserData(UserDTO userDTO) {

        myPageService.insertUserData(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));

    }

    /*1. 회원탈퇴*/
    /*@DeleteMapping("/delete")
    public ResponseEntity<CMRespDto<?>> deleteUserAllData(@RequestBody UserDTO userDTO,Model model){

        myPageService.deleteUserAllData(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered",true));

    }*/
    //=========================================================================================
    //1. 본인인증을 윈한 비밀번호 1회 확인 (로그인에 의해 본인인증은 따로 필요없음.)
    //2. 회원탈퇴 시 주의사항 고지
    //index 페이지는 탈퇴페이지에 대한 설정 변수 소스와 "회원탈퇴 시 주의사항","탈퇴사유", "패스워드 인증"에 대한 HTML 소스를 포함
    //process 페이지는 이용자가 입력한 탈퇴사유 저장, 이용자 ID/패스워드 확인, 개인정보 삭제에 대한 소스를 포함하고 있다

    @DeleteMapping(value = "/delete")
    public ResponseEntity<CMRespDto<?>> deleteUserAllData(@RequestBody UserDTO userDTO, RedirectAttributes rttr, HttpSession session) {
        myPageService.deleteUserAllData(userDTO);
        session.invalidate(); //설정된 세션의 값을 모두 무효화한다.
        rttr.addFlashAttribute("msg", "이용해주셔서 감사합니다."); //addFlashAttribute: url뒤에 전달한 값이 붙지 않는다
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully deleted", true));
    }

    //비밀번호 유효성검사
    @PostMapping("/delete/pwCheck")
    @ResponseBody
    public ResponseEntity<String> pwCheck(UserDTO userDTO) throws EvaluationException {

        // 사용자의 ID를 기반으로 암호화된 비밀번호를 가져옴
        String memberPw = myPageService.pwCheck(userDTO.getUserId());
        // 입력된 비밀번호와 데이터베이스에 저장된 해싱된 비밀번호를 비교
        boolean isPasswordMatch = BCrypt.checkpw(userDTO.getUserPw(), memberPw);



        // 비밀번호가 일치하지 않으면 문장출력
        if (!isPasswordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호가 일치하면 삭제페이지로 이동
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "http://localhost:8000/delete").build();
        //텍스트 처리해서 js로 처리
        //제공되는 텍스트를 직접 input으로 입력해서 텍스트 입력값을 js에서 비교하여 처리


    }



    /*

    //======================================================================================
       try {
            myPageService.deleteUserAllDataByIdAndPw(userId);
            return "Member deleted successfully";
        } catch (Exception e) {
            // 예외 처리: 회원 삭제에 실패한 경우
            return "Error deleting member: " + e.getMessage();
        }
    }*/

    /*2. 회원정보 수정*/
    //@PatchMapping("/member/update/{userIndex}")
    @PostMapping("/update")
    public ResponseEntity<CMRespDto<?>> updateMypageInfo(@RequestBody UserDTO userDTO) {

        myPageService.updateMypageInfo(userDTO);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    /*3. 내가 쓴 글 목록*/
    @PostMapping("/searchList/post")
    public ResponseEntity<CMRespDto<?>> searchMyPostListByIndex(@RequestBody BoardDTO boardDTO, int userIndex) {

        myPageService.searchMyPostListByIndex(userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));

    }

    /*4. 내가 쓴 댓글 목록*/
    @PostMapping("/searchList/reply")
    public ResponseEntity<CMRespDto<?>> searchMyReplyListByIndex(@RequestBody ReplyDTO replyDTO, int userIndex) {

        myPageService.searchMyReplyListByIndex(userIndex);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));

    }


}
