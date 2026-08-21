package com.gym.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.user.mapper.ClassOrderMapper;
import com.gym.user.pojo.ClassOrder;
import com.gym.user.pojo.ClassTable;
import com.gym.user.service.ClassOrderService;
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

    @Override
    public List<ClassOrder> selectByClassOrderId(Integer classId) {
        return classOrderMapper.selectByClassOrderId(classId);
    }
}
