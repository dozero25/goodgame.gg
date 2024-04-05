package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.exception.CustomInputUserGenderException;
import fourjo.idle.goodgame.gg.exception.CustomSameUserIdException;
import fourjo.idle.goodgame.gg.repository.UserRepository;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto registerUser(UserDto userDto) {
        duplicateUserId(userDto.getUserId());
        inputUserGender(userDto.getUserGender());

        userDto.setUserPw(new BCryptPasswordEncoder().encode(userDto.getUserPw()));
        userRepository.registerUser(userDto);

        return userDto;
    }

    public void duplicateUserId(String userId) {
        String result = userRepository.findUserByUserId(userId);

        if(result != null){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("username", "이미 존재하는 사용자 이름입니다.");

            throw new CustomSameUserIdException(errorMap);
        }

    }

    public void inputUserGender(String userGender){

        String gender = userGender.toLowerCase();

        if(gender.length() != 1){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("userGender", "입력 데이터 길이를 확인해주세요");

            throw new CustomInputUserGenderException(errorMap);

        } else if( (gender.equals("m") || gender.equals("w")) != true ){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("userGender", "올바른 단어를 사용해 입력해주세요");

            throw new CustomInputUserGenderException(errorMap);
        }
    }


}
