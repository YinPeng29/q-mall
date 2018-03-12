package com.bays.dao;

import com.bays.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yinpeng on 2017/10/24.
 */
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<Map> findAll();

    /**
     * 保存用户
     * @param user
     * @return
     */
    int insert (@Param("user") User user);

    /**
     * 用户验证
     * @param user
     * @return
     */
    User selectUser(@Param("user") User user);

    List<Map> findUserByName(@Param("username")String username);

    int updateStatus(@Param("status")int status,@Param("userId")String userId,@Param("uuid")String uuid);

    User selectOneUser(@Param("userId") String userId,@Param("uuid") String uuid);
}
