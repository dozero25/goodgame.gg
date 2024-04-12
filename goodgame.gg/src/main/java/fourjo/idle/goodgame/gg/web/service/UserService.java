package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.exception.CustomInputPasswordException;
import fourjo.idle.goodgame.gg.exception.CustomInputUserGenderException;
import fourjo.idle.goodgame.gg.exception.CustomSameUserIdException;
import fourjo.idle.goodgame.gg.repository.UserRepository;
import fourjo.idle.goodgame.gg.web.dto.account.EmpDto;
import fourjo.idle.goodgame.gg.web.dto.account.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto registerUser(UserDto userDto) {
        duplicateUserId(userDto.getUserId());
//        checkPassword(userDto.getUserPw());
//        inputUserGender(userDto.getUserGender());

        nullValueCheck(userDto);
        userDto.setUserPw(new BCryptPasswordEncoder().encode(userDto.getUserPw()));
        userRepository.registerUser(userDto);
        userRepository.saveUserRole(userDto.getUserId());
        return userDto;
    }

    public EmpDto registerEmp(EmpDto empDto) {
        duplicateUserId(empDto.getEmpId());
//        checkPassword(userDto.getUserPw());
//        inputUserGender(userDto.getUserGender());

        empDto.setEmpPw(new BCryptPasswordEncoder().encode(empDto.getEmpPw()));
        userRepository.registerEmp(empDto);
        userRepository.saveEmpRole(empDto.getEmpId());
        return empDto;
    }

    public void nullValueCheck(UserDto userDto) {
        String userId = userDto.getUserId().replaceAll(" ", "");
        String userPw = userDto.getUserPw().replaceAll(" ", "");
        String userNick = userDto.getUserNick().replaceAll(" ", "");
        String userEmail = userDto.getUserEmail().replaceAll(" ", "");

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("null value", "빈값을 확인 해주세요.");

        if(userId.equals("")){
            throw new CustomSameUserIdException(errorMap);

        } else if(userPw.equals("")){
            throw new CustomSameUserIdException(errorMap);

        } else if(userNick.equals("")){
            throw new CustomSameUserIdException(errorMap);

        } else if(userEmail.equals("")){
            throw new CustomSameUserIdException(errorMap);
        }
    }


    public void duplicateUserId(String userId) {
        String userResult = userRepository.findUserByUserIdForError(userId);
        String empResult = userRepository.findEmpByEmpIdForError(userId);

        if(userResult != null || empResult != null){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("username", "이미 존재하는 사용자 이름입니다.");

            throw new CustomSameUserIdException(errorMap);
        }
    }

    public void checkPassword(String userPw) {
        Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$");
        Matcher passMatcher = passPattern.matcher(userPw);

        if(!passMatcher.find()){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("Password Error", "비밀번호는 최소 8글자에서 최대 20글자, 영문자 1개 이상, 숫자 1개 이상, 특수문자1개 이상으로 구성되어야 합니다.");

            throw new CustomInputPasswordException(errorMap);
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
