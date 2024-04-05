package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminRepository;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public int userUpdate(UserDto dto) {
        return adminRepository.userUpdate(dto);
    }

    public int userDelete(UserDto dto) {
        return adminRepository.userDelete(dto);
    }

}
