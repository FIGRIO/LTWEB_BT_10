package vn.iotstar.springboot3_security6.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.iotstar.springboot3_security6.entity.UserInfo;
import java.util.*;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails { [cite: 1659]
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) { [cite: 1664]
        name = userInfo.getName(); [cite: 1665]
        password = userInfo.getPassword(); [cite: 1666]
        authorities = Arrays.stream(userInfo.getRoles().split(",")) [cite: 1667]
                .map(SimpleGrantedAuthority::new) [cite: 1677]
                .collect(Collectors.toList()); [cite: 1678]
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; } [cite: 1684, 1686]
    @Override public String getPassword() { return password; } [cite: 1692, 1693]
    @Override public String getUsername() { return name; } [cite: 1657, 1656]
    @Override public boolean isAccountNonExpired() { return true; } [cite: 1670, 1672]
    @Override public boolean isAccountNonLocked() { return true; } [cite: 1674, 1675]
    @Override public boolean isCredentialsNonExpired() { return true; } [cite: 1681, 1683]
    @Override public boolean isEnabled() { return true; } [cite: 1690, 1691]
}