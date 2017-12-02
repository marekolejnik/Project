package sk.stopangin.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.stopangin.dao.UserDao;
import sk.stopangin.model.User;


public class UserServiceNoMockTest {
    private UserDao userDao;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();
        userService = new UserService(userDao);
    }

    @Test
    public void createUser_and_check_whether_user_was_created() throws Exception {
        User user = new User(1l, "userLong");
        userService.createUser(user);
        User saved = userService.getUser(1l);
        Assert.assertEquals(user.getId(), saved.getId());
        Assert.assertEquals(user.getName(), saved.getName());
    }
}
