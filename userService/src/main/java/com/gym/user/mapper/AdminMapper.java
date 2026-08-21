package com.gym.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.user.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {
    Admin adminLogin(@Param("adminAccount") String adminAccount, @Param("adminPassword") String adminPassword);
}