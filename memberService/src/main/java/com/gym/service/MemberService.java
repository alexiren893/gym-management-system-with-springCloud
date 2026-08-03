package com.gym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.pojo.Member;

public interface MemberService extends IService<Member> {
    Member memberSelect(String memberAccount,String memberPassword);
}
