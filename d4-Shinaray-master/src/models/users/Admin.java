package models.users;

import RoleManagement.Role;

public class Admin extends User {
    private Role role = Role.ADMIN;
    private String string = "admin";

    public Admin() {}

    public Admin(String name, String email, String password) {
        super();
        setName(name);
        setEmail(email);
        setPassword(password);
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
