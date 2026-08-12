package com.gym.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.user.pojo.ClassOrder;
import com.gym.user.pojo.ClassTable;

import java.util.List;

public interface ClassOrderService extends IService<ClassOrder> {
    List<ClassOrder> listByMemberAccount(Integer memberAccount);
    Boolean insertByClassTableWithMemberName(ClassTable classTable, String memberName, Integer memberAccount);
    Boolean deleteByClassOrderIdWithMemberAccount(Integer classOrderId, Integer memberAccount);
}
