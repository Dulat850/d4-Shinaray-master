
package models.users;

import RoleManagement.Role;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String roled;

    public User(){
    }

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User(int id, String name, String email, String password) {
        this(name, email, password);
        setId(id);
    }

    public User(int id, String name, String email, String password, String role) {
        this(name, email, password);
        setRoled(role);
        setId(id);
    }

    public void setRoled(String role) {
        this.roled = role;
    }

    public String getRoled() {
        return roled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roled='" + roled + '\'' +
                '}';
    }
}
