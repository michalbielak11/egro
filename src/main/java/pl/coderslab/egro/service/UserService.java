package pl.coderslab.egro.service;

import pl.coderslab.egro.entity.User;


public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);
}