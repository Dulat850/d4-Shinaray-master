package models.users;

import RoleManagement.Role;

public class Sorter extends User {
    private Role role = Role.SORTER;

    public Sorter() {}

    public Sorter(String name, String email, String password) {
        super(name, email, password);
        setRole(role);
    }


    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return super.toString() + " " + role;
    }
}
