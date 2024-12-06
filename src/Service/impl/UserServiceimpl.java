package Service.impl;

import Dao.UserDao;
import Service.UserService;
import config.Validation;
import databasa.Database;
import enam.Role;
import exceptions.InvalidData;
import exceptions.NotFoundException;
import models.User;

public class UserServiceimpl implements UserService {
    private final UserDao userDao;

    public UserServiceimpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void savedDefaultAdmin() {
        userDao.save(
                new User("Admin@gmail.com",
                        "Admin123!",
                        "Online Shop",
                        Role.ADMIN)
        );
    }

    @Override
    public String sainUp(User user) {
        if (!Validation.checkEmail(user.getEmail())) {
            throw new InvalidData("invalid email");
        } else {
            User[] users = userDao.findAll();
            for (User u : users) {
                if (u.getEmail().equals(user.getEmail())) {
                    throw new IllegalArgumentException("Email already exists ");
                }
            }
        }
        if (!Validation.checkPassword(user.getPassword())) {
            throw new InvalidData("Invalid password");
        }
        userDao.save(user);
        return "Successfully";
    }

    @Override
    public User[] findAll() {
        return Database.users;
    }

    @Override
    public User singIn(String email, String password) {

        for (User user : findAll()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return user;

                }
            }

        }
        throw new NotFoundException("User not found");
    }
}
