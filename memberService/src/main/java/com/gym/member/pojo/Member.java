package com.gym.member.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Member {
    @TableId
    private Integer memberAccount;
    private String memberPassword;
    private String memberName;
    private String memberGender;
    private Integer memberAge;
    private Double memberHeight;
    private Double memberWeight;
    private String memberPhone;
    private String cardTime;
    private Integer cardClass;
    private Integer cardNextClass;
}