package com.gym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.pojo.User;

public interface UserService extends IService<User> {
    User userLogin(User user);
}
