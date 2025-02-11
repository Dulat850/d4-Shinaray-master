package models.users;

import RoleManagement.Role;

public class Costumer extends User{
    private Role role = Role.COSTUMER;
    private String string = "costumer";

    public Costumer() {}

    public Costumer(String name, String email, String password) {
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
