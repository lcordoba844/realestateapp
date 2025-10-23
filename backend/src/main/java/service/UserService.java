package service;
import dao.UserDao;
import model.User;
import util.PasswordUtils;

public class UserService {
    public User login(String username, String passwordAttempt) {
        User currentUser = UserDao.getUser(username);
        if (currentUser != null && PasswordUtils.verifyPassword(passwordAttempt, currentUser.getPassword())) {
            return currentUser;
        } else {
            return null;
        }
    }
    public void register(String username, String password, User.Role role) {
        User newUser = new User(username, password, role);
    }
}
