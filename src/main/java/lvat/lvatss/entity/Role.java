package lvat.lvatss.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "roles",
        uniqueConstraints = @UniqueConstraint(name = "uq_role_name", columnNames = "role_name"))
@Entity(name = "Role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private RoleName roleName;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "accounts_roles",
            inverseJoinColumns = @JoinColumn(name = "account_id",
                    referencedColumnName = "id",
                    table = "accounts",
                    foreignKey = @ForeignKey(name = "fk_role_id", value = ConstraintMode.CONSTRAINT)),
            joinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id",
                    table = "roles",
                    foreignKey = @ForeignKey(name = "fk_role_id", value = ConstraintMode.CONSTRAINT)))
    private List<Role> roleList;


    public Role() {
    }

    public Role(Integer id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public enum RoleName {
        ROLE_ADMIN {
            @Override
            public String toString() {
                return "ROLE_ADMIN";
            }
        },
        ROLE_USER {
            @Override
            public String toString() {
                return "ROLE_USER";
            }
        }
    }
}