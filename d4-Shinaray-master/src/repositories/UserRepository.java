package repositories;

import data.interfaces.IDB;
import models.users.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());

            st.execute();

            return true;
        } catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT id, name, email, password, role FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
                        rs.getString("role");
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = db.getConnection();  // Создаем соединение в блоке try-with-resources
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")// Предполагаем, что пароль хранится в базе в хэшированном виде
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return null;
    }

}
