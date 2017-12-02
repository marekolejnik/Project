package sk.stopangin.dao;

import sk.stopangin.ServiceException;
import sk.stopangin.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private List<User> users = new ArrayList<>();

    public void create(User user) {
        simulateNoResponse();
        if (user.getId() == null) {
            throw new RuntimeException("User id cannot be null.");
        }
        users.add(user);
    }

    public List<User> getAllUsers() {
        simulateNoResponse();
        return users;
    }

    public User getUser(Long id) {
        simulateNoResponse();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
        simulateNoResponse();
        users.remove(user);
    }

    private void simulateNoResponse() {
        throw new RuntimeException("No response from db.");
    }
}
