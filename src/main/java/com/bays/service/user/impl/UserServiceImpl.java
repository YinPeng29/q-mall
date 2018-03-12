package com.bays.service.user.impl;

import com.bays.dao.UserMapper;
import com.bays.model.User;
import com.bays.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<Map> findAll() {
        List<Map> all = userMapper.findAll();
        return all;
    }

    public int saveUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    public User selectUser(User user) {
        User resUser = userMapper.selectUser(user);
        if(resUser !=null){
            return resUser;
        }
        return null;
    }

    public List<Map> findUserByName(String username) {
        List<Map> userByName = userMapper.findUserByName(username);
        return userByName;
    }

    public int updateUser(int status, String userId,String uuid) {
        int updateUser = userMapper.updateStatus(status, userId,uuid);
        return updateUser;
    }

    /**
     * 验证邮件是否有效
     * @param userId
     * @param uuid
     * @return
     */
    public boolean validMail(String userId, String uuid) {
        int second = 60*60*24*3;  //三天
        User user = userMapper.selectOneUser(userId, uuid);
        if(user != null){
            long addTime = user.getAddTime().getTime();
            long now = new Date().getTime();
            int timeBetween  = (int)((now - addTime)/1000); //计算当前时间和创建时间的秒数 判断是否失效
            if(timeBetween > second){
                return false;
            }
            return true;
        }
        return false;

    }
}
