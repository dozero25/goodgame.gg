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


    public int userUpdateUserNickByUserIndex(UserDto dto) {
        return adminUserRepository.userUpdateUserNickByUserIndex(dto);
    }

    public int userDeleteByUserIndex(int userIndex) {

        return adminUserRepository.userDeleteByUserIndex(userIndex);
    }

    public List<UserDto> userSearchByUserNickAndEmailAndId(AdminUserSearchDTO adminUserSearchDTO  ) {

        return adminUserRepository.userSearchByUserNickAndEmailAndId(adminUserSearchDTO);
    }
    public UserDto userSelectOneByUserIndex(int userIndex  ) {

        return adminUserRepository.userSelectOneByUserIndex(userIndex);
    }
}
