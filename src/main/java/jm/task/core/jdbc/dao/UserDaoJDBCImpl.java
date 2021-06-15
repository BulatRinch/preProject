package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Util util = new Util();
    private String query = null;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        query = "CREATE TABLE  IF NOT EXISTS users (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            System.out.println("Таблица Users создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() throws SQLException {
        query = "DROP TABLE  IF EXISTS users";
        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            System.out.println("Таблица Users удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        query = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) throws SQLException{
        query = "DELETE FROM users WHERE ID=" + id + "";
        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
            System.out.println("User с ID=" + id + " удален");
            } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        query = "SELECT ID, name, lastName, age FROM users";

        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (User i: userList) {
            System.out.println(i);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException{
        query = "DELETE FROM users";
        try (Connection connection = DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD());
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.execute();
            System.out.println("Все записи с таблицы User удалены");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
