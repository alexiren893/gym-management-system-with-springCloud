package com.gym.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper extends BaseMapper<User> {
    User userLogin(User user);
}
