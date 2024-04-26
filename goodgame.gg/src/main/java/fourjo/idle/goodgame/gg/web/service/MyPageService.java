package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.MyPageRepository;
import fourjo.idle.goodgame.gg.web.dto.board.BoardDTO;
import fourjo.idle.goodgame.gg.web.dto.user.ReplyDTO;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    @Autowired
    private MyPageRepository myPageRepository;

    /*0. 더미데이터 생성*/
    public int insertUserData(UserDTO userDTO) {

        return myPageRepository.insertUserData(userDTO);
    }

    /*1. 회원탈퇴*/
    public int deleteUserAllData(int userIndex) {

        return myPageRepository.deleteUserAllData(userIndex);
    }
    public String pwCheck(int userId) {

        return myPageRepository.pwCheck(userId);
    }
    //========================================
    /*2. 회원정보 수정*/
    public int updateMypageInfo(UserDTO userDTO) {

        return myPageRepository.updateMypageInfo(userDTO);
    }

    /*3. 내가 쓴 글 목록*/
    public List<BoardDTO> searchMyPostListByIndex(int userIndex) {

        return myPageRepository.searchMyPostListByIndex(userIndex);
    }

   /* 4. 내가 쓴 댓글 목록*/
    public List<ReplyDTO> searchMyReplyListByIndex(int userIndex){
        System.out.println(userIndex);
        return myPageRepository.searchMyReplyListByIndex(userIndex);
    }

    /*5. 일단 하나만 불러오자*/
    public UserDTO selectOneData(int userIndex){
        return myPageRepository.selectOneData(userIndex);
    }


    public List<BoardDTO> selectAllData(int userIndex,BoardDTO boardDTO){
        return myPageRepository.selectAllData(userIndex, boardDTO);
    }



}
