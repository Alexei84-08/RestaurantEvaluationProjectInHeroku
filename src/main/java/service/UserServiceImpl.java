package service;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserCrud;
import to.UserTo;
import util.AuthorizedUser;

import java.util.List;

import static util.UserUtil.*;
import static util.ValidationUtil.*;
import static util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserCrud userCrud;

    @Autowired
    public UserServiceImpl(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return checkNotFoundWithId(userCrud.get(id), id);
    }

    @Override
    public User save(UserTo userTo) {
        LOG.info("save " + userTo);
        return userCrud.save(isNotNew(createNewFromTo(userTo)));
    }

    @Override
    public User update(UserTo userTo) {
        LOG.info("update " + userTo);
        checkNotFoundWithId(get(userTo.getId()), userTo.getId());
        User user = updateFromTo(get(userTo.getId()), userTo);
        return userCrud.save(user);
    }

    @Override
    public void delete(int id) {
        LOG.info("delete " + id);
        checkNotFoundWithId(get(id), id);
        userCrud.deleteById(id);
    }

    @Override
    public UserTo getUserTo(int id) {
        LOG.info("getUserTo " + id);
        User user = checkNotFoundWithId(userCrud.get(id), id);
        return asTo(user);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return userCrud.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        LOG.info("loadUserByUsername " + login);
        return new AuthorizedUser(getByLogin(login));
    }

    @Override
    public User getByLogin(String login) {
        LOG.info("getByLogin " + login);
        return checkNotFound(userCrud.getByLogin(login), "login "+login);
    }
}
