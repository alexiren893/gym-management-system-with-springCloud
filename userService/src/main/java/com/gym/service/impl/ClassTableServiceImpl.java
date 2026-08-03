package com.gym.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.mapper.ClassTableMapper;
import com.gym.pojo.ClassTable;
import com.gym.service.ClassTableService;
import org.springframework.stereotype.Service;

@Service
public class ClassTableServiceImpl extends ServiceImpl<ClassTableMapper, ClassTable> implements ClassTableService {
}
