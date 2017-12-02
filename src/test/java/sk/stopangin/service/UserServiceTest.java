package sk.stopangin.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.stopangin.ServiceException;
import sk.stopangin.dao.UserDao;
import sk.stopangin.model.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserDao userDao;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao);
    }

    @Test
    public void createUser_and_check_whether_user_was_created() throws Exception {
        User user = new User(1l, "userLong");
        when(userDao.getUser(1l)).thenReturn(user);
        userService.createUser(user);
        User saved = userService.getUser(1l);
        verify(userDao, times(1)).getUser(1l);
        assertEquals(user.getId(), saved.getId());
        assertEquals(user.getName(), saved.getName());
    }

    @Test(expected = ServiceException.class)
    public void createUser_with_short_username() throws Exception {
        User user = new User(1l, "user");
        userService.createUser(user);
    }


    @Test(expected = ServiceException.class)
    public void createUser_and_check_wrong_id() throws Exception {
        User user = new User(1l, "userLong");
        when(userDao.getUser(1l)).thenReturn(user);
        userService.createUser(user);
        userService.getUser(2l);
    }

    @Test(expected = ServiceException.class)
    public void createUser_without_id() throws Exception {
        User user = new User(null, "userLong");
        doThrow(new RuntimeException("User id cannot be null")).when(userDao).create(user);
        userService.createUser(user);
    }
}
