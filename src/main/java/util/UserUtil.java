package util;

import model.Roles;
import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import to.UserTo;

public class UserUtil {
    public static User createNewFromTo(UserTo newUser) {
        return new User(newUser.getId(), newUser.getName(), newUser.getLogin().toLowerCase(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Roles.ROLE_USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setLogin(userTo.getLogin());
        user.setPassword(userTo.getPassword());
        user.setEmail(userTo.getEmail());
        user.setName(userTo.getName());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getLogin(), user.getPassword(), user.getEmail(), user.getName());
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
