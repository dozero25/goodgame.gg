package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.MyPageRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.ReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    @Autowired
    private MyPageRepository myPageRepository;

    /*0. 더미데이터 생성*/
    public int insertUserData(UserDTO userDTO) {

        return myPageRepository.insertUserData(userDTO);
    }

    /*1. 회원탈퇴*/
    /*public int deleteUserAllData(UserDTO userDTO) {

        return myPageRepository.deleteUserAllData(userDTO);
    }*/

    public int deleteUserAllData(UserDTO userDTO) {

        return myPageRepository.deleteUserAllData(userDTO);
    }
    public String pwCheck(String userId) {

        return myPageRepository.pwCheck(userId);
    }
    //========================================
    /*2. 회원정보 수정*/
    public int updateMypageInfo(UserDTO userDTO) {

        return myPageRepository.updateMypageInfo(userDTO);
    }

    /*3. 내가 쓴 글 목록*/
    public BoardDTO searchMyPostListByIndex(int userIndex) {

        return myPageRepository.searchMyPostListByIndex(userIndex);
    }

   /* 4. 내가 쓴 댓글 목록*/
    public ReplyDTO searchMyReplyListByIndex(int userIndex){
        return myPageRepository.searchMyReplyListByIndex(userIndex);
    }



}
