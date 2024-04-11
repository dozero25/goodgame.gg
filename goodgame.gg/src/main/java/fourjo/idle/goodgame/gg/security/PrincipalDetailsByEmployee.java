package fourjo.idle.goodgame.gg.security;

import fourjo.idle.goodgame.gg.entity.EmployeeMst;
import fourjo.idle.goodgame.gg.entity.RoleDtl;
import fourjo.idle.goodgame.gg.entity.RoleMst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PrincipalDetailsByEmployee implements UserDetails {

    @Getter
    private final EmployeeMst emp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        List<RoleDtl> roleDtlList = emp.getRoleDtl();
        for(int i = 0; i < roleDtlList.size(); i++){
            RoleDtl dtl = roleDtlList.get(i);
            RoleMst roleMst = dtl.getRoleMst();
            String roleName = roleMst.getRoleName();

            GrantedAuthority role = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return roleName;
                }
            };
            authorities.add(role);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return emp.getEmpPw();
    }

    @Override
    public String getUsername() {
        return emp.getEmpId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
