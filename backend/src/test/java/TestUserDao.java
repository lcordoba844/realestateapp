
import dao.UserDao;
import util.UserAlreadyExistsException;


public class TestUserDao {
    static void main() {
        try {
            UserDao.addNewUser("admin", "admin");
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}