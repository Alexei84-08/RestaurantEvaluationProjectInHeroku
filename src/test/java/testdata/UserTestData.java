package testdata;

import model.Roles;
import model.User;
import to.UserTo;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    private static Set<Roles> ROLES_USER = new HashSet<>(Collections.singletonList(Roles.ROLE_USER));
    private static Set<Roles> ROLES_ADMIN = new HashSet<>(Collections.singletonList(Roles.ROLE_ADMIN));
    public static User USER_ADMIN = new User(1, "Admin", "admin", "addmin@mail.com", "asd", ROLES_ADMIN, null);
    public static User USER_USER_R = new User(2, "User", "user", "usser@mail.com", "asd", ROLES_USER, null);
    public static User USER_ADMIN_N = new User(100, "Admin", "adminn", "admin@mail.com", "admin", ROLES_ADMIN, null);
    public static User USER_USER = new User(101, "User", "userr", "user@mail.com", "user", ROLES_USER, null);
    public static User USER_ALEX = new User(102, "Alex", "alex", "alex@mail.com", "alex", ROLES_USER, null);
    public static User USER_JOHN_DOE = new User(103, "John_Doe", "john", "johndoe@mail.com", "doe", ROLES_USER, null);
    public static User USER_JANE_DOE = new User(104, "Jane_Doe", "jane", "Jane_Doe", "doe", ROLES_USER, null);

    public static UserTo TO_NEW_USER = new UserTo(null, "newUser", "newuser", "newuser@mail.com", "NewUser");
    public static UserTo TO_NEW_USER_EX = new UserTo(5000, "newUser", "newuser", "newuser@mail.com", "NewUser");
    public static UserTo TO_USER_ADMIN_UPDATE = new UserTo(1, "AdminUpdate", "admin", "addmin@mail.com", "asd");
    public static UserTo TO_USER_ADMIN_UPDATE_NOT_FOUND = new UserTo(15, "AdminUpdate", "admin", "addmin@mail.com", "asd");
    public static UserTo TO_USER_ADMIN_N = new UserTo(100, "adminn", "admin", "admin@mail.com", "Admin");

    public static List<User> USERS = asList(USER_ADMIN, USER_USER_R, USER_ADMIN_N,USER_USER, USER_ALEX, USER_JOHN_DOE, USER_JANE_DOE);
    public static List<User> USERS_WITHOUT_USER_JANE_DOE = asList(USER_ADMIN, USER_USER_R, USER_ADMIN_N,USER_USER, USER_ALEX, USER_JOHN_DOE);

    public static int NO_USER = 15;

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).containsOnlyElementsOf(expected);
    }
}
