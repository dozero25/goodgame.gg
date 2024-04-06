package fourjo.idle.goodgame.gg.web.service;

import fourjo.idle.goodgame.gg.repository.AdminUserRepository;
import fourjo.idle.goodgame.gg.web.dto.AdminUserSearchDTO;
import fourjo.idle.goodgame.gg.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;


    public int AdminUserUpdate(UserDto dto) {
        return adminUserRepository.AdminUserUpdate(dto);
    }

    public int AdminUserDelete(int user_index) {

        return adminUserRepository.AdminUserDelete(user_index);
    }
    public List<UserDto> AdminUserSelectAll(  ) {

        return adminUserRepository.AdminUserSelectAll();
    }
    public List<UserDto> AdminUserSearch(AdminUserSearchDTO adminUserSearchDTO  ) {

        return adminUserRepository.AdminUserSearch(adminUserSearchDTO);
    }
    public UserDto AdminUserSelectOne(int user_index  ) {

        return adminUserRepository.AdminUserSelectOne(user_index);
    }
}
