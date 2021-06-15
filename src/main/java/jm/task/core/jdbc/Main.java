package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Александр", "Ветров",(byte)50);
        userService.saveUser("Владимир", "Игнатьев",(byte)24);
        userService.saveUser("Жаргал", "Дашиев",(byte)25);
        userService.saveUser("Ирина", "Насанова",(byte)39);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
