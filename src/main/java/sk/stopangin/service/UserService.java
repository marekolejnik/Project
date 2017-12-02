package sk.stopangin.service;

import sk.stopangin.ServiceException;
import sk.stopangin.dao.UserDao;
import sk.stopangin.model.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) {

        if (user.getName().length() < 5) {
            throw new ServiceException("User name must be longer than 5 characters.");
        }
        try {
            userDao.create(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteUser(Long id) {
        User user = getUser(id);
        userDao.deleteUser(user);
    }

    public User getUser(Long id) {
        User user = userDao.getUser(id);
        if (user == null) {
            throw new ServiceException("User not found for id: " + id + ".");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
