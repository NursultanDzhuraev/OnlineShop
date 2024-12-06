package Dao;


import models.User;

public interface UserDao {

     void save(User user);
     User[] findAll();
}
