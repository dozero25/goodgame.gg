package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.MyPageRepository;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyPageService {

    @Autowired
    private MyPageRepository myPageRepository;

    /*회원탈퇴*/
    public void deleteMemberAllDataByIdAndPw(String id) {

    }

    /*회원정보 수정*/
    public void updateMyPageByPrivacy(String userDTO) {

        myPageRepository.updateMyPageByPrivacy(userDTO);
    }
    
    /*내가 쓴 글 목록 확인*/
    public UserDTO searchMyWriteListByID(UserDTO userDTO) {

        return  myPageRepository.searchMyWriteListByID(userDTO);
    }

    /*내가 쓴 댓글 목록 확인*/
    public UserDTO searchMyWriteListByID(UserDTO userDTO) {

        return  myPageRepository.searchMyWriteListByID(userDTO);
    }


}
