package fourjo.idle.goodgame.gg.security;

import fourjo.idle.goodgame.gg.entity.RoleMst;
import fourjo.idle.goodgame.gg.entity.UserMst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class PrincipalDetailsByUser implements UserDetails {

    @Getter
    private final UserMst user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        int compareRoleId = user.getRoleId();
        RoleMst roleMst = new RoleMst();
        roleMst.setRoleId(compareRoleId);
        String roleName = roleMst.getRoleName();

        GrantedAuthority role = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return roleName;
            }
        };
        authorities.add(role);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
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
