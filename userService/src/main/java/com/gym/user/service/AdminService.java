package com.gym.user.service;

import com.gym.user.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2026-08-02
 */
public interface AdminService extends IService<Admin> {
    Admin adminLogin(String adminAccount, String adminPassword);

}
