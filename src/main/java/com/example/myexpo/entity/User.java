package com.example.myexpo.entity;

import java.util.Set;

public class User {

    private Integer id;


    private String username;
    private String email;
    private String password;

    private boolean active;

    private Set<Role> roles;

    public static Builder builder() {
        return new User().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return username;
    }

    public enum Role {
        USER, ADMIN;

        public String getAuthority() {
            return name();
        }
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Integer id) {
            User.this.id = id;
            return this;
        }

        public Builder username(String username) {
            User.this.username = username;

            return this;
        }

        public Builder password(String password) {
            User.this.password = password;

            return this;
        }

        public Builder email(String email) {
            User.this.email = email;

            return this;
        }

        public Builder active(boolean active) {
            User.this.active = active;

            return this;
        }

        public Builder roles(Set<Role> roles) {
            User.this.roles = roles;

            return this;
        }

        public User build() {
            return User.this;
        }

    }
}
