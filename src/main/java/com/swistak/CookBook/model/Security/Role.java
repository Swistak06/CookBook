package com.swistak.CookBook.model.Security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;

    private String roleName;

    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.id = roleId;
        this.roleName = roleName;
    }

    public Role(String roleName, Set<UserRole> userRoleSet) {
        this.roleName = roleName;
        this.userRoleSet = userRoleSet;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserRole> userRoleSet = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
