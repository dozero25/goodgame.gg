package fourjo.idle.goodgame.gg.security;

import fourjo.idle.goodgame.gg.entity.UserMst;
import fourjo.idle.goodgame.gg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsServiceByUser implements UserDetailsService {

    // 나중에 employee로 변경
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserMst user = userRepository.findUserByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException("회원정보를 확인할 수 없습니다.");
        }

        return new PrincipalDetailsByUser(user);
    }
}
