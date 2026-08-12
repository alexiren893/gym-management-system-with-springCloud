package com.gym.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.user.mapper.ClassTableMapper;
import com.gym.user.pojo.ClassTable;
import com.gym.user.service.ClassTableService;
import org.springframework.stereotype.Service;

@Service
public class ClassTableServiceImpl extends ServiceImpl<ClassTableMapper, ClassTable> implements ClassTableService {
}
