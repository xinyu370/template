package com.example.demo.config.auth;

import com.example.demo.pojo.Authorities;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private String tenantCode;
    private String enabled;
    private String delFlag;
    private List<Authorities> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(a->new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(enabled);
    }
}
