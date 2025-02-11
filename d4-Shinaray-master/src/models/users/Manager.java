package models.users;

import RoleManagement.Role;

public class Manager extends User {
    private Role role = Role.MANAGER;

    public Manager() {}

    public Manager(String name, String email, String password) {
        super(name, email, password);
        setRole(role);
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
