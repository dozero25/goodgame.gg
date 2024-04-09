package fourjo.idle.goodgame.gg.security;

import fourjo.idle.goodgame.gg.entity.EmployeeMst;
import fourjo.idle.goodgame.gg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsServiceByEmployee implements UserDetailsService {

    // 나중에 employee로 변경
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        EmployeeMst emp = userRepository.findEmpByEmpId(userId);

        if (emp == null) {
            throw new UsernameNotFoundException("회원정보를 확인할 수 없습니다.");
        }

        return new PrincipalDetailsByEmployee(emp);
    }
}
