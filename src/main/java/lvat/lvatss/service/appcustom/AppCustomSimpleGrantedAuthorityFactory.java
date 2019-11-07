package lvat.lvatss.service.appcustom;

import lvat.lvatss.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AppCustomSimpleGrantedAuthorityFactory {
    private static final GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority(Role.RoleName.ROLE_ADMIN.toString());
    private static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority(Role.RoleName.ROLE_USER.toString());

    public static GrantedAuthority getAuthority(Role.RoleName roleName) {
        if (roleName == Role.RoleName.ROLE_ADMIN) {
            return ROLE_ADMIN;
        }
        if (roleName == Role.RoleName.ROLE_USER) {
            return ROLE_USER;
        }
        return null;
    }

    public static GrantedAuthority getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public static GrantedAuthority getRoleUser() {
        return ROLE_USER;
    }
}
