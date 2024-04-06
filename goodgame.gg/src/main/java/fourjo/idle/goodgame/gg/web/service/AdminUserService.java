package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminRepository;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminUserService {

    @Autowired
    private AdminRepository adminRepository;


    public int AdminUserUpdate(UserDto dto) {
        return adminRepository.AdminUserUpdate(dto);
    }

    public int AdminUserDelete(int user_index) {

        return adminRepository.AdminUserDelete(user_index);
    }
    public List<UserDto> AdminUserSelectAll(  ) {

        return adminRepository.AdminUserSelectAll();
    }
    public List<UserDto> AdminUserSearchList(String user_nick  ) {

        return adminRepository.AdminUserSearchList(user_nick);
    }
    public UserDto AdminUserSelectOne(int user_index  ) {

        return adminRepository.AdminUserSelectOne(user_index);
    }
}
