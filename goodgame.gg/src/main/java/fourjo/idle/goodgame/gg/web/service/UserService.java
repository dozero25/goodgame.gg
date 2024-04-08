package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.UserRepository;
import fourjo.idle.goodgame.gg.web.dto.admin.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDto registerUser(UserDto userDto) {
        userDto.setUserPw(new BCryptPasswordEncoder().encode(userDto.getUserPw()));

//        userRepository.registerUser(userDto);
        System.out.println(userRepository.registerUser(userDto));
        return userDto;
    }


}
