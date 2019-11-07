package lvat.lvatss.config;

import lvat.lvatss.service.AccountService;
import lvat.lvatss.service.appcustom.AppCustomSimpleGrantedAuthorityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppCustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private final AccountService accountService;
    private static final GrantedAuthority ROLE_ADMIN = AppCustomSimpleGrantedAuthorityFactory.getRoleAdmin();
    private static final GrantedAuthority ROLE_USER = AppCustomSimpleGrantedAuthorityFactory.getRoleUser();

    @Autowired
    public AppCustomWebSecurityConfigurerAdapter(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
//        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").anonymous();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/logout").not().anonymous();
        http.authorizeRequests().antMatchers("/user/**").hasRole(ROLE_ADMIN.getAuthority());
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole(ROLE_ADMIN.getAuthority(), ROLE_USER.getAuthority());
//        super.configure(http);

        http.authorizeRequests().and().formLogin()
                .passwordParameter("password")
                .usernameParameter("username")
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_login")
                .defaultSuccessUrl("/user")
                .failureUrl("/login?message=error");
        http.logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/login?message=logout");

    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
