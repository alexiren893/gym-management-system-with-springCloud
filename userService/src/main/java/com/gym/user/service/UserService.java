package com.gym.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.user.pojo.User;

public interface UserService extends IService<User> {
    User userLogin(User user);
}
