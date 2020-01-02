package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
}
