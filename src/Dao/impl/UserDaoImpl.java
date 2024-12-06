package Dao.impl;

import Dao.UserDao;
import databasa.Database;
import models.User;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        Database.users = Arrays.copyOf(Database.users,Database.users.length+1);
        Database.users[Database.users.length-1] = user;
    }
    @Override
    public User[] findAll() {
        return Database.users;
    }
}
