package com.gym.equipment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.equipment.mapper.EquipmentMapper;
import com.gym.equipment.pojo.Equipment;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {
}
