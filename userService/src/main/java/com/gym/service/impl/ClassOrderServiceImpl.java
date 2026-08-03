package com.gym.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.mapper.ClassOrderMapper;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.service.ClassOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassOrderServiceImpl extends ServiceImpl<ClassOrderMapper, ClassOrder> implements ClassOrderService {

    private final ClassOrderMapper classOrderMapper;

    public ClassOrderServiceImpl(ClassOrderMapper classOrderMapper) {
        this.classOrderMapper = classOrderMapper;
    }
    @Override
    public List<ClassOrder> listByMemberAccount(Integer memberAccount) {

        return classOrderMapper.listByMemberAccount(memberAccount);
    }

    @Override
    public Boolean insertByClassTableWithMemberName(ClassTable classTable, String memberName, Integer memberAccount) {
        return classOrderMapper.insertByClassTableWithMemberName(classTable, memberName, memberAccount);
    }

    @Override
    public Boolean deleteByClassOrderIdWithMemberAccount(Integer classOrderId, Integer memberAccount) {
        return classOrderMapper.deleteByClassOrderIdWithMemberAccount(classOrderId, memberAccount);
    }
}
