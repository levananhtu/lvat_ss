package lvat.lvatss.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Table(name = "accounts",
        uniqueConstraints = {@UniqueConstraint(name = "uq_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_username", columnNames = "username")})
@Entity(name = "Account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank
    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @Column(name = "email")
    @Email
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "password")
    @NotNull
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @Transient
    @NotNull
    @NotBlank
    private String confirmPassword;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id",
                    referencedColumnName = "id",
                    table = "accounts",
                    foreignKey = @ForeignKey(name = "fk_role_id", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id",
                    table = "roles",
                    foreignKey = @ForeignKey(name = "fk_role_id", value = ConstraintMode.CONSTRAINT)))
    private Set<Role> roleSet;

    public Account() {
    }

    public Account(Long id, @NotBlank @NotNull @Size(min = 5, max = 20) String username, @Email @NotBlank @NotNull @Size(max = 100) String email, @NotNull @NotBlank @Size(min = 5, max = 20) String password, @NotNull @NotBlank String confirmPassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Account(@NotBlank @NotNull @Size(min = 5, max = 20) String username, @Email @NotBlank @NotNull @Size(max = 100) String email, @NotNull @NotBlank @Size(min = 5, max = 20) String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
