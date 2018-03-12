package com.bays.service.user;

import com.bays.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<Map> findAll();
    int saveUser(User user);
    User selectUser(User user);
    List<Map> findUserByName(String username);
    int updateUser(int status,String userId,String uuid);
    boolean validMail(String userId,String uuid);
}
