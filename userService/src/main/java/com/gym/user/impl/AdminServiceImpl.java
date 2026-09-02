package com.gym.user.impl;

import com.gym.user.pojo.Admin;
import com.gym.user.mapper.AdminMapper;
import com.gym.user.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin adminLogin(String adminAccount, String adminPassword) {
        return adminMapper.adminLogin(adminAccount, adminPassword);
    }
}
