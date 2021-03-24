package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.*;
import static org.hibernate.loader.Loader.SELECT;

public class UserDaoHibernateImpl implements UserDao{

    public UserDaoHibernateImpl(){
    }

    @Override
    public void createUsersTable() {
        String script = " CREATE TABLE IF NOT EXISTS `mydb`.`users` (\n" +
                "  `userId` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `userName` VARCHAR(45) NOT NULL,\n" +
                "  `userLastName` VARCHAR(45) NOT NULL,\n" +
                "  `userAge` INT NOT NULL,\n" +
                "  PRIMARY KEY (`userId`));";
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(script);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String script = "DROP TABLE IF EXISTS users";
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(script);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery ("DELETE FROM users WHERE usersId = " + id);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        String script = "SELECT FROM users";
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = (List<User>) session.createQuery(script).list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String script = "TRUNCATE TABLE users";
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(script);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
