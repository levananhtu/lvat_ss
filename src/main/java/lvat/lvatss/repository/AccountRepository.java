package lvat.lvatss.repository;

import lvat.lvatss.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    <T> Optional<T> findByUsernameOrEmailIgnoreCase(String username, String email, Class<T> type);
}
