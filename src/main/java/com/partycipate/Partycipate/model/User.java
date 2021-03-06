package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>() ;

//    Constructor
    public User() {}

    private User(Builder builder) {
        //ToDo fix user to implement SignupForm
        this.user_id=builder.id;
        this.username=builder.username;
        this.email=builder.email;
        this.password=builder.password;
        this.roles=builder.roles;
        this.surveys=builder.surveys;
        this.name=builder.name;
    }
//    Builder
    public static class Builder {


        private int id = 0;
        private String username = "username";
        private String email = "user@email.de";
        private String password = "password";
        private String name = "name";
        private Set<Role> roles = null;
        private Set<Survey> surveys = null;

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
        public User.Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }
        public User.Builder name(String name) {
            this.name = name;
            return this;
        }
        public User.Builder surveys(Set<Survey> surveys){
            this.surveys=surveys;
            return this;
        }

        public User build() {
        return new User(this);
    }
}

//      Getter & Setter

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public Set<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}