package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.exception.CustomInputPasswordException;
import fourjo.idle.goodgame.gg.exception.CustomInputUserGenderException;
import fourjo.idle.goodgame.gg.exception.CustomSameUserIdException;
import fourjo.idle.goodgame.gg.repository.AccountRepository;
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
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public UserDto registerUser(UserDto userDto) {
        duplicateUserId(userDto.getUserId());
//        checkPassword(userDto.getUserPw());
        inputUserGender(userDto.getUserGender());

        nullValueCheck(userDto);
        userDto.setUserPw(new BCryptPasswordEncoder().encode(userDto.getUserPw()));
        accountRepository.registerUser(userDto);
        accountRepository.saveUserRole(userDto.getUserId());
        return userDto;
    }

    public EmpDto registerEmp(EmpDto empDto) {
        duplicateUserId(empDto.getEmpId());
//        checkPassword(userDto.getUserPw());
//        inputUserGender(userDto.getUserGender());

        empDto.setEmpPw(new BCryptPasswordEncoder().encode(empDto.getEmpPw()));
        accountRepository.registerEmp(empDto);
        accountRepository.saveEmpRole(empDto.getEmpId());
        return empDto;
    }

    public void nullValueCheck(UserDto userDto) {
        String userId = userDto.getUserId().replaceAll(" ", "");
        String userPw = userDto.getUserPw().replaceAll(" ", "");
        String userNick = userDto.getUserNick().replaceAll(" ", "");
        String userEmail = userDto.getUserEmail().replaceAll(" ", "");

        Map<String, String> errorMap = new HashMap<>();
        if(userId.equals("") || userPw.equals("") || userNick.equals("") || userEmail.equals("")){
            errorMap.put("null value", "빈값을 확인 해주세요.");
            throw new CustomSameUserIdException(errorMap);
        }
    }


    public void duplicateUserId(String userId) {
        String userResult = accountRepository.findUserByUserIdForError(userId);
        String empResult = accountRepository.findEmpByEmpIdForError(userId);

        Map<String, String> errorMap = new HashMap<>();
        if(userResult != null || empResult != null){
            errorMap.put("username", "이미 존재하는 사용자 이름입니다.");
            throw new CustomSameUserIdException(errorMap);
        }
    }

    public void checkPassword(String userPw) {
        Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$");
        // 비밀번호는 최소 8글자에서 최대 20글자, 영문자 1개 이상, 숫자 1개 이상, 특수문자1개 이상으로 구성되어야 합니다.
        Matcher passMatcher = passPattern.matcher(userPw);

        Map<String, String> errorMap = new HashMap<>();
        if(!passMatcher.find()){
            errorMap.put("Password Error", "비밀번호를 다시 설정해주세요.");
            throw new CustomInputPasswordException(errorMap);
        }
    }

    public void inputUserGender(String userGender){
        String gender = userGender.toLowerCase();
        Map<String, String> errorMap = new HashMap<>();

        if(gender.length() != 1){
            errorMap.put("userGender", "입력 데이터 길이를 확인해주세요");
            throw new CustomInputUserGenderException(errorMap);

        } else if( (gender.equals("m") || gender.equals("w")) != true ){
            errorMap.put("userGender", "올바른 단어를 사용해 입력해주세요");
            throw new CustomInputUserGenderException(errorMap);
        }
    }

}