package com.gym.user.dto;

import lombok.Data;

@Data
public class Member {
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

