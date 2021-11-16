package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> created_rooms;
    private List<Chatroom> socialized_rooms;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created_rooms = new ArrayList<>();
        this.socialized_rooms = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreated_rooms() {
        return created_rooms;
    }

    public void setCreated_rooms(List<Chatroom> created_rooms) {
        this.created_rooms = created_rooms;
    }

    public List<Chatroom> getSocialized_rooms() {
        return socialized_rooms;
    }

    public void setSocialized_rooms(List<Chatroom> socialized_rooms) {
        this.socialized_rooms = socialized_rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && Objects.equals(password, user.password) && Objects.equals(created_rooms, user.created_rooms) && Objects.equals(socialized_rooms, user.socialized_rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, created_rooms, socialized_rooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created_rooms=" + created_rooms +
                ", socialized_rooms=" + socialized_rooms +
                '}';
    }
}
