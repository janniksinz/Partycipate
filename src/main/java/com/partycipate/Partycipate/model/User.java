package com.partycipate.Partycipate.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            //ToDo check if type Identity oder ID mit UUID nicht besser wäre
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int user_id;
    private String name;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Survey> surveys = new HashSet<>() ;

    @OneToOne(mappedBy = "user")
    private AuthToken authToken;

    private User(Builder builder) {
        this.user_id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password= builder.password;

    }

    public User() {}

    public User(String name, String username, String email, String encode) {
        this.name=name;
        this.username=username;
        this.email=email;
        this.password=encode;
    }

    public static class Builder {

        private int id = 0;
        private String username = "User";
        private String email = "user@email.de";
        private String password = "password";


        public User.Builder id(int id) {
            this.id = id;
            return this;
        }

        public User.Builder username(String username) {
            this.username = username;
            return this;
        }

        public User.Builder email(String email) {
            this.email = email;
            return this;
        }
        public User.Builder password(String password) {
            this.password = password;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }

    public Set<Survey> getSurveys() {
        return surveys;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}