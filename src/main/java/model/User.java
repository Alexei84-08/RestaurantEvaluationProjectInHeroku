package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.parents.AbstractNamedEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.EnumSet;
import java.util.Set;

@NamedEntityGraph(name = User.GRAPH_WITH_VOTE_HISTORY_AND_ROLES, includeAllAttributes = true)

@NamedQueries({
        @NamedQuery(name = User.GET_BY_ID, query = "SELECT u FROM User u WHERE u.id=?1"),
        @NamedQuery(name = User.BY_LOGIN, query = "SELECT u FROM User u WHERE u.login=?1"),
        @NamedQuery(name = User.VOTES, query = "SELECT v FROM Vote v WHERE v.user.id = ?1 ORDER BY v.date, v.time DESC")
})

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "users_unique_email_idx"),
        @UniqueConstraint(columnNames = "login", name = "users_unique_login_idx")
})
public class User extends AbstractNamedEntity {

    public static final String GRAPH_WITH_VOTE_HISTORY_AND_ROLES = "User.withVoteHistoryAndRoles";

    public static final String GET_BY_ID = "User.getById";

    public static final String BY_LOGIN = "User.getByLogin";

    public static final String VOTES = "User.getVotes";


    @Column(name = "login", nullable = false)
    @NotBlank
    @Length(min = 3)
    private String login;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 3)
    private String password;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Roles> roles;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy(value = "date, time DESC")
    private Set<Vote> voteHistory;

    public User() {
    }

    public User(Integer id, String name,  @Length(min = 3) String login, @Email  String email,  @Length(min = 3) String password, Set<Roles> roles, Set<Vote> voteHistory) {
        super(id, name);
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.voteHistory = voteHistory;
    }

    public User(Integer id,  String name,  @Length(min = 3) String login, @Email  String email,  @Length(min = 3) String password, Roles role, Roles... roles) {
        super(id, name);
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public User(Integer id, String name, String email, String password, Set<Roles> roles, Set<Vote> voteHistory) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.voteHistory = voteHistory;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Vote> getVoteHistory() {
        return voteHistory;
    }

    public void setVoteHistory(Set<Vote> voteHistory) {
        this.voteHistory = voteHistory;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "User{" +
//                "login='" + login + '\'' +
//                ", email='" + email + '\'' +
//                ", roles=" + roles +
//                ", voteHistory=" + voteHistory +
//                ", name='" + name + '\'' +
//                ", id=" + id +
//                '}';
//    }
}
