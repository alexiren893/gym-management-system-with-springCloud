package com.gym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;

import java.util.List;

public interface ClassOrderService extends IService<ClassOrder> {
    List<ClassOrder> listByMemberAccount(Integer memberAccount);
    Boolean insertByClassTableWithMemberName(ClassTable classTable, String memberName, Integer memberAccount);
    Boolean deleteByClassOrderIdWithMemberAccount(Integer classOrderId, Integer memberAccount);
}
