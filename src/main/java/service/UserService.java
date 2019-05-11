package service;

import model.User;
import org.springframework.security.core.userdetails.UserDetails;
import to.UserTo;

import java.util.List;

public interface UserService {
    User get(int id);
    User save(UserTo userTo);
    User update(UserTo userTo);
    void delete(int id);
    List<User> getAll();
    UserTo getUserTo(int id);
    User getByLogin(String login);
    UserDetails loadUserByUsername(String login);
}
