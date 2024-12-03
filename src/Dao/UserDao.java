package Dao;


import models.User;

public interface UserDao {
    // Register
     void save(User user);
     User[] findAll();
}
