package Service;

import models.User;

public interface UserService {
    void  savedDefaultAdmin();
    String sainUp(User user);

    User[] findAll();

    User singIn(String email, String password);
}
