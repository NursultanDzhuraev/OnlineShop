package Service;

import models.User;

public interface UserService {
    String sainUp(User user);

    User[] findAll();
}
