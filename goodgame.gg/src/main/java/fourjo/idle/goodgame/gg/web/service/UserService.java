package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.UserRepository;
import fourjo.idle.goodgame.gg.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDTO registerUser(UserDTO userDto) {
        userDto.setUserPw(new BCryptPasswordEncoder().encode(userDto.getUserPw()));

//        userRepository.registerUser(userDto);
        System.out.println(userRepository.registerUser(userDto));
        return userDto;
    }


}
