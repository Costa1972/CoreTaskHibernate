package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


//        userService.dropUsersTable();
//        userService.createUsersTable();
//        userDaoHibernate.saveUser("Costa", "Vashchuk", (byte) 48);
        userService.cleanUsersTable();
    }
}
