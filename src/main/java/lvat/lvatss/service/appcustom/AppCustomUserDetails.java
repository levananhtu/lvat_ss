package lvat.lvatss.service.appcustom;

import lvat.lvatss.entity.Account;
import lvat.lvatss.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AppCustomUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<GrantedAuthority> roleSet;

    public AppCustomUserDetails(Long id, String username, String password, String email, Set<GrantedAuthority> roleSet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleSet = roleSet;
    }

    public static AppCustomUserDetails create(Account account) {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        for (Role role : account.getRoleSet()) {
            authoritySet.add(AppCustomSimpleGrantedAuthorityFactory.getAuthority(role.getRoleName()));
        }
        return new AppCustomUserDetails(account.getId(), account.getUsername(), account.getPassword(), account.getEmail(), authoritySet);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
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
        return true;
    }
}
