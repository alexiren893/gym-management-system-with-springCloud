package com.gym.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Employee {
    @TableId
    private Integer employeeAccount;
    private String employeeName;
    private String employeeGender;
    private Integer employeeAge;
    private String entryTime;
    private String staff;
    private String employeeMessage;
}

