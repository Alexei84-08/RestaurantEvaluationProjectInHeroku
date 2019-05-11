package service;

import model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.AuthorizedUser;
import util.exception.IllegalRequestDataException;
import util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static testdata.UserTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @Test
    void get() {
        assertThat(userService.get(USER_USER_R.getId())).isEqualToIgnoringGivenFields(USER_USER_R, "voteHistory");
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.get(NO_USER));
    }

    @Test
    void save() {
        User created = userService.save(TO_NEW_USER);
        User actual = userService.get(created.getId());
        assertThat(actual).isEqualToIgnoringGivenFields(created, "voteHistory");
    }

    @Test
    void saveIllegalArgumentException() {
        assertThrows(IllegalRequestDataException.class, () -> userService.save(TO_NEW_USER_EX));
    }

    @Test
    void update() {
        User expected = userService.update(TO_USER_ADMIN_UPDATE);
        assertThat(userService.get(USER_ADMIN.getId())).isEqualToIgnoringGivenFields(expected, "voteHistory");
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () -> userService.update(TO_USER_ADMIN_UPDATE_NOT_FOUND));
    }

    @Test
    void delete() {
        userService.delete(USER_JANE_DOE.getId());
        assertMatch(userService.getAll(), USERS_WITHOUT_USER_JANE_DOE);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(NO_USER));
    }

    @Test
    void getUserTo() {
        assertThat(userService.getUserTo(USER_ADMIN_N.getId())).isEqualToIgnoringGivenFields(TO_USER_ADMIN_N);
    }

    @Test
    void getUserToNotFound() {
        assertThrows(NotFoundException.class, () -> userService.getUserTo(NO_USER));
    }

    @Test
    void getAll() {
        assertMatch(userService.getAll(), USERS);
    }

    @Test
    void loadUserByUsername() {
        assertThat(userService.loadUserByUsername(USER_JOHN_DOE.getLogin())).isEqualTo(new AuthorizedUser(USER_JOHN_DOE));
    }

    @Test
    void loadUserByUsernameNotFound() {
        assertThrows(NotFoundException.class, () -> userService.loadUserByUsername("noLogin"));
    }

    @Test
    void getByLogin() {
        assertThat(userService.getByLogin(USER_ALEX.getLogin())).isEqualTo(USER_ALEX);
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NotFoundException.class, () -> userService.getByLogin("noLogin"));
    }
}