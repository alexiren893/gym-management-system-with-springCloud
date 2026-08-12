package com.gym.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassTable {
    @TableId(type = IdType.INPUT)
    private Integer classId;
    private String className;
    private String classBegin;
    private String classTime;
    private String coach;
}
