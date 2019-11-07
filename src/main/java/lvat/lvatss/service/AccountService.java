package lvat.lvatss.service;

import lvat.lvatss.entity.Account;
import lvat.lvatss.repository.AccountRepository;
import lvat.lvatss.service.appcustom.AppCustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return AppCustomUserDetails.create(accountRepository.findByUsernameOrEmailIgnoreCase(s, s, Account.class).orElseThrow(() -> new UsernameNotFoundException("Account doesn't exist")));
    }
}
