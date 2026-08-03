package com.gym.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassOrderMapper extends BaseMapper<ClassOrder> {
    List<ClassOrder> listByMemberAccount(Integer memberAccount);
    Boolean insertByClassTableWithMemberName(@Param("classTable") ClassTable classTable, @Param("memberName") String memberName, @Param("memberAccount") Integer memberAccount);
    Boolean deleteByClassOrderIdWithMemberAccount(@Param("classOrderId") Integer classOrderId, @Param("memberAccount") Integer memberAccount);
}
